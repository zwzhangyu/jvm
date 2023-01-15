package byecode.zy.attribute;


import byecode.zy.type.U2;
import byecode.zy.type.U4;

public class ConstantValue_attribute {

    private U2 attribute_name_index;
    private U4 attribute_length;
    private U2 constantvalue_index;

    public U2 getAttribute_name_index() {
        return attribute_name_index;
    }

    public void setAttribute_name_index(U2 attribute_name_index) {
        this.attribute_name_index = attribute_name_index;
    }

    public U4 getAttribute_length() {
        return attribute_length;
    }

    public void setAttribute_length(U4 attribute_length) {
        this.attribute_length = attribute_length;
    }

    public U2 getConstantvalue_index() {
        return constantvalue_index;
    }

    public void setConstantvalue_index(U2 constantvalue_index) {
        this.constantvalue_index = constantvalue_index;
    }
}
