package cn.com.leadfar.cms.backend.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.backend.dao.MemberDao;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;

public class MemberDaoForMyBatisImpl extends BaseDao implements MemberDao {

	@Override
	public void addMember(Member member) {
		
		//���Ȳ�ѯ���ݿ⣬�ǳ��Ƿ��Ѿ����ڣ�
		Member m = findMemberByNickname(member.getNickname());
		if(m != null){
			throw new RuntimeException("��ע����û��ǳ��Ѿ����ڣ��뻻һ������");
		}
		
		//����ǳƲ����ڣ�������ע�ᣡ
		add(member);
	}

	@Override
	public void delMembers(String[] ids) {
		del(Member.class,ids);
	}

	@Override
	public PagerVO findAllMembers() {
		Map params = new HashMap();
		return findPaginated(Member.class.getName()+".findPaginated", params);
	}

	@Override
	public Member findMemberById(int id) {
		return (Member)findById(Member.class, id);
	}

	@Override
	public void updateMember(Member member) {
		update(member);
	}

	@Override
	public void updatePassword(int memberId, String oldPass, String newPass) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			Member m = (Member)session.selectOne(Member.class.getName()+".findById", memberId);
			if(m.getPassword().equals(oldPass)){ //ֻ������ľ����������ݿ��е�����һ�£��������������
				m.setPassword(newPass); //��Ϊ�µ�����
				session.update(Member.class.getName()+".updatePassword", m); //���µ����ݿ���
			}else{
				throw new RuntimeException("���������벻��ȷ��������");
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			throw new RuntimeException(e.getMessage(),e); //�����׳��쳣���Ա�ͻ��˿��Դ�����쳣�����û���ʾ��Ϣ
		} finally{
			session.close();
		}
	}

	@Override
	public Member findMemberByNickname(String nickname) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			Member m = (Member)session.selectOne(Member.class.getName()+".findByNickname", nickname);
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return null;
	}

}
