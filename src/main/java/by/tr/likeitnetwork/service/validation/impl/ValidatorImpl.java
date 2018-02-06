package by.tr.likeitnetwork.service.validation.impl;

import by.tr.likeitnetwork.entity.input.Input;
import by.tr.likeitnetwork.service.validation.Validator;
import by.tr.likeitnetwork.service.validation.rule.Rule;

import java.util.*;

public class ValidatorImpl implements Validator {
    private Set<Rule> ruleSet;

    public boolean isValid(Input input) {
        return ruleSet.stream().allMatch(rule -> rule.isValid(input));
    }

    public ValidatorImpl() {
        ruleSet = new LinkedHashSet<>();
    }

    public void setRules(Rule... ruleArray) {
        ruleSet.clear();
        ruleSet.addAll(Arrays.asList(ruleArray));
    }

}
