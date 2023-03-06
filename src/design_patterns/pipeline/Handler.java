package design_patterns.pipeline;

public interface Handler<I, O> {
    O process(I input);
}
