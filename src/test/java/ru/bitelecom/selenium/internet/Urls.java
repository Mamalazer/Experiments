package ru.bitelecom.selenium.internet;

public enum Urls {

    addRemoveElements("http://the-internet.herokuapp.com/add_remove_elements/"),
    basicAuth("http://admin:admin@the-internet.herokuapp.com/basic_auth"),
    checkBoxes("http://the-internet.herokuapp.com/checkboxes"),
    contextMenu("http://the-internet.herokuapp.com/context_menu");

    Urls(String url) {
        this.value = url;
    }

    String value;
}
