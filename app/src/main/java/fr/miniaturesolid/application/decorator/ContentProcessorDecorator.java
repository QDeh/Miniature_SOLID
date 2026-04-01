package fr.miniaturesolid.application.decorator;

public abstract class ContentProcessorDecorator implements ContentProcessor {

    protected final ContentProcessor wrapped;


    public ContentProcessorDecorator(ContentProcessor wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String process(String content) {
        return wrapped.process(content);
    }

}
