package design_patterns_java.creational.abstractfactory;
class MacButton implements Button {
    @Override
    public void click() {
        System.out.println("Mac Button clicked!");
    }
}