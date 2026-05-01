package annotations;

public @interface Validate {
    int min() default 3;

    int max() default 20;

    String msg() default "Input does not meet the validation requirements";

    String value() default "";
}
