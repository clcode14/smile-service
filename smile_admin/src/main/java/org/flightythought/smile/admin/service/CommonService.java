package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.FileInfo;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.bean.SelectItemOption;
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

    /**
     * 上传文件到服务器
     *
     * @param file  文件
     * @param model 模块
     * @throws FlightyThoughtException
     */
    FileInfo uploadFile(MultipartFile file, String model, HttpSession session) throws FlightyThoughtException;

    /**
     * 上传文件到服务器
     *
     * @param files 文件
     * @param model 模块
     * @throws FlightyThoughtException
     */
    List<FileInfo> uploadFiles(List<MultipartFile> files, String model, HttpSession session) throws FlightyThoughtException;


    /**
     * 删除图片
     *
     * @param imageId 图片ID
     */
    void deleteImage(Integer imageId);

    /**
     * 获取解决方案下拉选
     */
    List<SelectItemOption> getSolutionOptions();
}
