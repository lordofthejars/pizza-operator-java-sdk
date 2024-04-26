package org.acme;

import io.javaoperatorsdk.operator.api.reconciler.Reconciler;
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl;

import java.util.List;

import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.ControllerConfiguration;
import io.javaoperatorsdk.operator.api.reconciler.dependent.Dependent;


@ControllerConfiguration(
        dependents = {
                @Dependent(type = PizzaDeploymentDependantResource.class, name = PizzaDeploymentDependantResource.NAME)
        }
)
public class PizzaOperatorReconciler implements Reconciler<PizzaOperatorCustomResource> {

    public UpdateControl<PizzaOperatorCustomResource> reconcile(PizzaOperatorCustomResource pizza,
                                                     Context<PizzaOperatorCustomResource> context) {


       /** List<String> toppings = pizza.getSpec().getToppings();
        String sauce = pizza.getSpec().getSauce();

        // Here you could use Fabric8 to create a pod, a deployment, multiple pods,  or anything but in this simple case we just print the cpizza
        
        String msg = String.format("Pizza %s ordered with %s toppings and %s sauce", pizza.getMetadata().getName(), toppings, sauce);
        System.out.println(msg);
        
        pizza.setStatus(new PizzaOperatorStatus());
        pizza.getStatus().setMessage(msg);
        **/
        return UpdateControl.updateStatus(pizza);
    }
}
