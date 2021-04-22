package Framework;

public class Binding<Type, Data extends CryptoData> implements ModelListener<Data>, CustomComponentListener<Type>
{
    private final String type;
    private final CustomComponent<Type> component;
    private final Class<Type> componentTypeClass;
    private final Model<Data> model;


    private Binding(Model<Data> model, String type, CustomComponent<Type> component, Class<Type> classType)
    {
        this.type = type;
        this.component = component;
        this.componentTypeClass = classType;
        this.model = model;
        component.addCustomComponentListener(this);
        model.addModelChangeListener(this);
    }

    private final Type getSubscribedData(ModelEvent<Data> event)
    {
        return componentTypeClass.cast(event.getCryptoData().getData().get(type));
    }

    @Override
    public void modelChanged(ModelEvent<Data> event)
    {
        if(event.getUpdatedParts().contains(type))
        {
            setComponentData(event);
        }
    }

    private void setComponentData(ModelEvent<Data> event)
    {
        boolean originalEditable = component.isEditable();
        component.setEditable(false);
        component.setValue(getSubscribedData(event));
        component.setEditable(originalEditable);

    }


    @Override
    public void CryptoComponentValueChanged(CustomComponentValueChanged<Type> event)
    {
        this.model.updateModelData(this.type,event.getValue());
    }

    @Override
    public void modelSync(ModelEvent<Data> event)
    {
        setComponentData(event);
    }

    public static<Data extends CryptoData,Type> void bind(Model<Data> model, String type, CustomComponent<Type> component, Class<Type> classType)
    {
        new Binding(model, type, component, classType);
    }
}
