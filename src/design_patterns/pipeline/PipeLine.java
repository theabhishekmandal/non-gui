package design_patterns.pipeline;

public class PipeLine<I, O> {
    protected Handler<I, O> handler;

    protected PipeLine(Handler<I, O> handler) {
        this.handler = handler;
    }

    <K> PipeLine<I, K> addHandler(Handler<O, K> handler) {
        return new PipeLine<>(input -> handler.process(this.handler.process(input)));
    }

    O execute(I input) {
        return handler.process(input);
    }
}
