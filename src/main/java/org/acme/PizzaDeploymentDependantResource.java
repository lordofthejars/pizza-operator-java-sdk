package org.acme;

import io.fabric8.kubernetes.api.model.*;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.processing.dependent.Creator;
import io.javaoperatorsdk.operator.processing.dependent.kubernetes.KubernetesDependentResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaDeploymentDependantResource extends KubernetesDependentResource<Pod, PizzaOperatorCustomResource>
        implements Creator<Pod, PizzaOperatorCustomResource> {

    public static final String NAME = "pizzapod";

    public PizzaDeploymentDependantResource() {
        super(Pod.class);
    }

    @Override
    protected Pod desired(PizzaOperatorCustomResource resource, Context<PizzaOperatorCustomResource> context) {

        final String app = resource.getMetadata().getName();
        final String sauce = resource.getSpec().getSauce();
        final List<String> toppings = resource.getSpec().getToppings();
        final Map<String, String> labels = new HashMap<>();
        labels.put("app", app);
        final ObjectMetaBuilder objectMetaBuilder = new ObjectMetaBuilder().withName(app + "-pod")
                .withNamespace(resource.getMetadata().getNamespace()).withLabels(labels);
        final ContainerBuilder containerBuilder = new ContainerBuilder().withName("pizza-maker")
                .withImage("quay.io/lordofthejars/pizza-maker:1.0.0").withCommand("/work/application")
                .withArgs("--sauce=" + sauce, "--toppings=" + String.join(",", toppings));
        final PodSpecBuilder podSpecBuilder = new PodSpecBuilder().withContainers(containerBuilder.build())
                .withRestartPolicy("Never");
        final PodBuilder podBuilder = new PodBuilder().withMetadata(objectMetaBuilder.build())
                .withSpec(podSpecBuilder.build());
        return podBuilder.build();
    }

}
