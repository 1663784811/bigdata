package com.cyyaw.test;

import com.cyyaw.tx.phone.PhoneDao;
import com.cyyaw.tx.phone.PhoneDaoImpl;
import com.cyyaw.tx.phone.PhoneEntity;
import com.cyyaw.tx.phone.task.TaskController;
import com.cyyaw.tx.phone.task.page.HandlePage;
import com.cyyaw.tx.phone.task.page.HomeHandle;

import java.util.ArrayList;
import java.util.List;

public class AA {


    public static void main(String[] args) {


        PhoneDao phoneDao = new PhoneDaoImpl();

        List<PhoneEntity> phoneEntities = phoneDao.phoneList();

        for (int i = 0; i < phoneEntities.size(); i++) {
            PhoneEntity entity = phoneEntities.get(i);
            String name = entity.getName();


            TaskController taskController = new TaskController();
            taskController.setPhoneDao(phoneDao);
            taskController.initTask(name);


            List<HandlePage> handlePage = new ArrayList<>();
            handlePage.add(new HomeHandle());


            taskController.setHandlePage(handlePage);


            taskController.execTask();


        }

    }


}
