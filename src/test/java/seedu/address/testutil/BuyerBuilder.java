package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building client objects.
 */
public class BuyerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_APPOINTMENT = "2022-05-01-12-00";

    private Name name;
    private Phone phone;
    private Set<Tag> tags;
    private Appointment appointment;
    private List<PropertyToBuy> properties;

    /**
     * Creates a {@code clientBuilder} with the default details.
     */
    public BuyerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        appointment = new Appointment(DEFAULT_APPOINTMENT);
        tags = new HashSet<>();
        properties = new ArrayList<>();
    }

    /**
     * Initializes the clientBuilder with the data of {@code clientToCopy}.
     */
    public BuyerBuilder(Buyer buyerToCopy) {
        name = buyerToCopy.getName();
        phone = buyerToCopy.getPhone();
        appointment = buyerToCopy.getAppointment();
        tags = new HashSet<>(buyerToCopy.getTags());
        properties = new ArrayList<>(buyerToCopy.getPropertiesToBuy());
    }

    /**
     * Sets the {@code Name} of the {@code client} that we are building.
     */
    public BuyerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code client} that we are building.
     */
    public BuyerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code client} that we are building.
     */
    public BuyerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code client} that we are building.
     */
    public BuyerBuilder withAppointment(String appointment) {
        this.appointment = new Appointment(appointment);
        return this;
    }

    /**
     * Sets the {@code Properties} of the {@code buyer} that we are building.
     */
    public BuyerBuilder withProperties(PropertyToBuy... properties) {
        this.properties = new ArrayList<>(Arrays.asList(properties));
        return this;
    }

    public Buyer build() {
        return new Buyer(name, phone, appointment, tags, properties);
    }

}