package design_patterns_java.creational.abstractfactory;

class WindowsCheckbox implements Checkbox {
    @Override
    public void toggle() {
        System.out.println("Windows Checkbox toggled!");
    }
}