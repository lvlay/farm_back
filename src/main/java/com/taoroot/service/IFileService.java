package com.taoroot.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: taoroot
 * @date: 2018/2/5
 * @description:
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
