package edu.odu.cs.cs330.items;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 */
@SuppressWarnings({
    "PMD.AtLeastOneConstructor",
    "PMD.BeanMembersShouldSerialize",
    "PMD.JUnitAssertionsShouldIncludeMessage",
    "PMD.JUnitTestContainsTooManyAsserts",
    "PMD.LocalVariableCouldBeFinal",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LawOfDemeter"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestConsumable
{
    private Consumable tea;

    @BeforeEach
    public void setUp()
    {
        tea = new Consumable();
        tea.setName("Green Tea");
        tea.setEffect("Wake Up");
        tea.setNumberOfUses(10);
    }

    @Test
    public void testDefaultConstructor()
    {
        Consumable imagination = new Consumable();

        Item  genericRef = (Item) imagination;

        assertTrue(imagination.isStackable());
        assertTrue(genericRef.isStackable());

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test

        // I should really check display() and/or operator<< here. However, I will
        // do that in a separate `testDisplay` function
    }

    @Test
    public void testClone()
    {
        Consumable moreTea = (Consumable) tea.clone();

        assertThat(moreTea, is(not(sameInstance(tea))));
        assertThat(moreTea.isStackable(), is(true));
        assertThat(moreTea.getName(), equalTo("Green Tea"));
        assertThat(moreTea.getEffect(), equalTo("Wake Up"));
        assertThat(moreTea.getNumberOfUses(), is(10));

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test
    }

    @Test
    public void testToString()
    {
        String expected = String.join(
            System.lineSeparator(),
            "  Nme: Green Tea",
            "  Eft: Wake Up",
            "  Use: 10",
            ""
        );

        assertThat(tea.toString(), equalTo(expected));
    }

    @Test
    public void testEquals()
    {
        Consumable generic = new Consumable();

        Consumable moreTea = (Consumable) tea.clone();
        moreTea.setNumberOfUses(12);

        assertThat(tea, not(equalTo(generic)));
        assertThat(tea, is(equalTo(moreTea)));

        moreTea = (Consumable) tea.clone();
        moreTea.setEffect("Relax");
        assertThat(tea, is(not(equalTo(moreTea))));

        // Test Consumable vs non-Consumable object
        assertThat(moreTea, not(equalTo("")));
    }

    @Test
    public void testHashCode()
    {
        Consumable moreTea = (Consumable) tea.clone();
        moreTea.setNumberOfUses(12);

        assertThat(tea.hashCode(), equalTo(moreTea.hashCode()));

        moreTea = (Consumable) tea.clone();
        moreTea.setName("Earl Grey");
        assertThat(tea.hashCode(), not(equalTo(moreTea.hashCode())));

        moreTea = (Consumable) tea.clone();
        moreTea.setEffect("Relax");
        assertThat(tea.hashCode(), not(equalTo(moreTea.hashCode())));
    }
}
