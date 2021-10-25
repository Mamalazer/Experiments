package ru.bitelecom.selenium.internet;

public enum Urls {

    addRemoveElements("http://the-internet.herokuapp.com/add_remove_elements/");

    Urls(String url) {
        this.value = url;
    }

    String value;
}
