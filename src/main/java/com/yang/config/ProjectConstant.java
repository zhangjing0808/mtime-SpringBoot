package com.yang.config;

/**
 * 项目常量
 */
public final class ProjectConstant {
    public static final String BASE_PACKAGE = "com.yang";//项目基础包名称
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";//Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".route";//Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".mapper.Mapper";//Mapper插件基础接口的完全限定名

    //上传图片路径
    public static final String BASE_DISK_FOLDER = "D:/IDEA/worksapce/Java_project";
    public static final String BASE_CLOUD_URL = "http://ponlc6wcj.bkt.clouddn.com/";

    public static final String MOVIE_UPLOADED_FOLDER = "img/movie_img/";
    public static final String USER_UPLOADED_FOLDER = "img/user_img/";
    public static final String ACTOR_UPLOADED_FOLDER = "img/actor_img/";

    //七牛云
    public static final String ACCESS_KEY = "lwYJyNBPoYjz9zw3PsB2gd14SWORIWOH9pdmp2jc";
    public static final String SECRET_KEY = "7uSGzGa0UCxQ6oJB7pvkniIvaFy2EwRg_-R62TNG";
    public static final String BUCKET = "mtime";
}
