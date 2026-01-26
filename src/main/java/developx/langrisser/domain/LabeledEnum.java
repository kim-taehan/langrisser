package developx.langrisser.domain;

public interface LabeledEnum {

    String name();
    default String code(){
        return name();
    }
    String label();
    default String displayName(){
        return label();
    }
}
