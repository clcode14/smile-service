package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.database.entity.Images;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CommonService {
    /**
     * 上传图片到服务器
     *
     * @param image 图片
     * @return 图片实体类
     */
    ImageInfo uploadImage(MultipartFile image, String type, HttpSession session) throws FlightyThoughtException;

    /**
     * 上传图片到服务器
     *
     * @param images 图片集合
     * @return 图片实体类
     */
    List<ImageInfo> uploadImages(List<MultipartFile> images, String type, HttpSession session) throws FlightyThoughtException;
}
