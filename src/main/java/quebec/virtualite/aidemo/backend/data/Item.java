package quebec.virtualite.aidemo.backend.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

@Entity
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    protected Item()
    {
    }

    public Item(long id, String text)
    {
        this.id = id;
        this.text = text;
    }

    public Item(String text)
    {
        this(0, text);
    }

    @Override
    public boolean equals(Object thatObject)
    {
        if (!(thatObject instanceof Item))
            return false;

        Item that = (Item) thatObject;

        if (this.id != 0 && that.id != 0 && this.id != that.id)
            return false;

        return (this.text == null)
               ? that.text == null
               : this.text.equals(that.text);
    }

    @Override
    public String toString()
    {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    public long getId()
    {
        return id;
    }

    public Item setId(long id)
    {
        this.id = id;
        return this;
    }

    public String getText()
    {
        return text;
    }

    public Item setText(String text)
    {
        this.text = text;
        return this;
    }
}
