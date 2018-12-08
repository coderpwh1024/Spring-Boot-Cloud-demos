package com.why.MyProject.service;


import com.why.MyProject.common.MyException;
import com.why.MyProject.entity.User;
import com.why.MyProject.mapper.UserMapper;
import com.why.MyProject.util.ThreadPoolFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional(readOnly = true)
public class TestServiceImpl implements ITestService {

    @Autowired
    private UserMapper userMapper;


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {

        ThreadPoolFactory factory = new ThreadPoolFactory();

        boolean notNull = false;
//        List<User> userList = new ArrayList<User>();
        List<User> userList = new CopyOnWriteArrayList<User>();


        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }

        factory.addTaskSubmit(()->{
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                User user = new User();

                if (row.getCell(0).getCellType() != 1) {
                    throw new MyException("导入失败(第" + (r + 1) + "行,姓名请设为文本格式)");
                }
                String name = row.getCell(0).getStringCellValue();

                if (name == null || name.isEmpty()) {
                    throw new MyException("导入失败(第" + (r + 1) + "行,姓名未填写)");
                }

                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String phone = row.getCell(1).getStringCellValue();
                if (phone == null || phone.isEmpty()) {
                    throw new MyException("导入失败(第" + (r + 1) + "行,电话未填写)");
                }
                String add = row.getCell(2).getStringCellValue();
                if (add == null) {
                    throw new MyException("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
                }

                Date date;
                if (row.getCell(3).getCellType() != 0) {
                    throw new MyException("导入失败(第" + (r + 1) + "行,入职日期格式不正确或未填写)");
                } else {
                    date = row.getCell(3).getDateCellValue();
                }
                String des = row.getCell(4).getStringCellValue();
                user.setName(name);
                user.setPhone(phone);
                user.setAddress(add);
                user.setEnrolDate(date);
                user.setDes(des);
                userList.add(user);
            }

            return 1;
        });


        factory.addTaskSubmint(new Runnable() {
            @Override
            public void run() {
                userMapper.insertBatch(userList);
                System.out.println(" 插入 " + userList);
            }
        });

       /* factory.addTaskSubmint(()->{
                userMapper.insertBatch(userList);
                System.out.println(" 插入 "+userList);
        });*/

        /*userMapper.insertBatch(userList);
        System.out.println(" 插入 "+userList);*/




     /*   for (User userResord : userList) {
            String name = userResord.getName();
            int cnt = userMapper.selectByName(name);
            if (cnt == 0) {
//                userMapper.addUser(userResord);
                userMapper.insertBatch(userResord);
                System.out.println(" 插入 "+userResord);
            } else {
                userMapper.updateUserByName(userResord);
                System.out.println(" 更新 "+userResord);
            }
        }*/
        return notNull;
    }

    @Override
    public boolean batchImports(String fileName, MultipartFile file) throws Exception {
        return false;
    }

    //判断是否是csv文件
    private boolean isCsv(String fileName) {
        return fileName.matches("^.+\\.(?i)(csv)$");
    }


}
