package fr.miniaturesolid.application.decorator;

public class EscapeHtmlContentDecorator extends ContentProcessorDecorator {

    public EscapeHtmlContentDecorator(ContentProcessor wrapped) {
        super(wrapped);
    }

    @Override
    public String process(String content) {
        String result = super.process(content);
        if (result == null) return null;
        return result
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");

    }
}
