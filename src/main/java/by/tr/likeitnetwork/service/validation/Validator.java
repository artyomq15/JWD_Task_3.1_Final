package by.tr.likeitnetwork.service.validation;

import by.tr.likeitnetwork.entity.input.Input;
import by.tr.likeitnetwork.service.validation.rule.Rule;

public interface Validator<T extends Input> {
    boolean isValid(T input);
    void setRules(Rule... ruleArray);
}
