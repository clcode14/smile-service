package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.DiseaseClassDetailSimple;
import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.springframework.data.domain.Page;
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
    ImageInfo uploadImage(MultipartFile image, String model, HttpSession session) throws FlightyThoughtException;

    /**
     * 上传图片到服务器
     *
     * @param images 图片集合
     * @return 图片实体类
     */
    List<ImageInfo> uploadImages(List<MultipartFile> images, String model, HttpSession session) throws FlightyThoughtException;

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


    Page<DiseaseClassDetailSimple> getDiseaseClassDetails(PageFilterDTO pageFilterDTO);
}
