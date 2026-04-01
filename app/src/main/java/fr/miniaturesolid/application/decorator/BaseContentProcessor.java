package fr.miniaturesolid.application.decorator;

public class BaseContentProcessor implements ContentProcessor {
    @Override
    public String process(String content) {
        return content;
    }
}
