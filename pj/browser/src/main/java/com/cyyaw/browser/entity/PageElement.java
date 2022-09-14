package com.cyyaw.browser.entity;

import lombok.Data;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class PageElement {

    private AtomicInteger index;

    private List<WebElement> elementList;

    public WebElement getElement() {
        WebElement webElement = elementList.get(index.get());
        return webElement;
    }

    public int size() {
        if (null != elementList) {
            return elementList.size();
        } else {
            return 0;
        }
    }

    public WebElement getIndex(int index) {
        return elementList.get(index);
    }

    public WebElement getNext() {
        int i = index.get();
        if(i<size()){
            index.set(i+1);
            return elementList.get(index.get());
        }else {
            return null;
        }
    }


}
