package ru.bitelecom.selenide.internet;

public enum Urls {

    addRemoveElements("http://the-internet.herokuapp.com/add_remove_elements/"),
    basicAuth("http://admin:admin@the-internet.herokuapp.com/basic_auth"),
    checkBoxes("http://the-internet.herokuapp.com/checkboxes"),
    contextMenu("http://the-internet.herokuapp.com/context_menu"),
    disappearingElements("http://the-internet.herokuapp.com/disappearing_elements"),
    dragAndDrop("http://crossbrowsertesting.github.io/drag-and-drop.html"),
    dropdownList("http://the-internet.herokuapp.com/dropdown"),
    dynamicControls("http://the-internet.herokuapp.com/dynamic_controls"),
    entryAd("http://the-internet.herokuapp.com/entry_ad"),
    downloadFile("http://the-internet.herokuapp.com/download"),
    uploadFile("http://the-internet.herokuapp.com/upload"),
    formAuthentication("http://the-internet.herokuapp.com/login"),
    frames("http://the-internet.herokuapp.com/frames"),
    horizontalSlider("http://the-internet.herokuapp.com/horizontal_slider"),
    hovers("http://the-internet.herokuapp.com/hovers"),
    inputNumbers("http://the-internet.herokuapp.com/inputs"),
    jQuery("http://the-internet.herokuapp.com/jqueryui/menu"),
    jsAlert("http://the-internet.herokuapp.com/javascript_alerts"),
    multipleWindows("http://the-internet.herokuapp.com/windows"),
    notificationMessage("http://the-internet.herokuapp.com/notification_message_rendered");

    private Urls(String url) {
        this.url = url;
    }

    String url;
}
