package ru.bitelecom.selenium.internet;

public enum Urls {

    addRemoveElements("http://the-internet.herokuapp.com/add_remove_elements/"),
    basicAuth("http://admin:admin@the-internet.herokuapp.com/basic_auth"),
    checkBoxes("http://the-internet.herokuapp.com/checkboxes"),
    contextMenu("http://the-internet.herokuapp.com/context_menu"),
    disappearingElements("http://the-internet.herokuapp.com/disappearing_elements"),
    dragAndDrop("http://crossbrowsertesting.github.io/drag-and-drop.html"),
    dropdownList("http://the-internet.herokuapp.com/dropdown"),
    dynamicControls("http://the-internet.herokuapp.com/dynamic_controls");

    Urls(String url) {
        this.value = url;
    }

    String value;
}
