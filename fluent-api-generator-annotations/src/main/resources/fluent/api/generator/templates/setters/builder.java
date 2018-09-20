{% set productType = empty(var) ? type : var.type %}
{% set packageName = (packageName == "") ? (empty(var) ? type.packageName : var.packageName) : packageName %}
{% set className = (className == "") ? concat(productType.simpleName, capitalize(methodName), "er") : className %}
package {{ packageName }};
import javax.annotation.Generated;
import fluent.api.End;

@Generated("Generated code using {{ templatePath }}")
public final class {{ className }}{% if empty(productType.parameterVariables) %}{% else %}<{% for t in productType.parameterVariables %}{{ t }}{% endfor %}>{% endif %} {

    private final {{ productType }} object;

    public {{ className }}({{ productType }} object) {
        this.object = object;
    }

{% for setter in productType.methods %}{% if setter.name.startsWith("set") and setter.parameters.size == 1 %}
    public {{ className }} {{ setter.propertyName }}({{ setter.parameters[0].type }} value) {
        object.{{ setter.name }}(value);
        return this;
    }
{% endif %}{% endfor %}
    @End
    public {{ productType }} {{ methodName }}() {
        return {{ method.name }}(object);
    }

}