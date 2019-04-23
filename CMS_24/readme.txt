DAO对象不包含状态，只是纯粹的操作，即使多个线程同时访问同一个DAO对象，也完全没有问题，
所以，没有必要每次访问DAO，都创建一个DAO对象
因此，在PropertiesBeanFactory中初始化所有的bean，每次getBean，只是直接返回一个已经
初始化好的对象即可！