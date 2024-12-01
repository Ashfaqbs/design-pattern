package design_patterns_java.creational.abstractfactory;
class MacCheckbox implements Checkbox {
    @Override
    public void toggle() {
        System.out.println("Mac Checkbox toggled!");
    }
}