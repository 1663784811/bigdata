package com.cyyaw.browser.entity;

import lombok.Data;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class PageElement {

    private AtomicInteger index;

    private List<WebElement> elementList;

    public WebElement getElement(){
        WebElement webElement = elementList.get(index.get());
        return webElement;
    }

    public void aa (){

    }



}
