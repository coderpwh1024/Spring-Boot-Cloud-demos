package com.why.MyProject.service;


import org.springframework.web.multipart.MultipartFile;

public interface ITestService {

    boolean batchImport(String fileName, MultipartFile file) throws Exception;

    boolean batchImports(String fileName, MultipartFile file) throws Exception;
}
