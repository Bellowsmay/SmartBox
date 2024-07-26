package smartbox.components;

public interface IStack {
    public void push(Double num);
    public void pop();
    public Double top();
    public void clear();
    public Boolean isEmpty();
}