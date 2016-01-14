package com.memoizrlabs;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import rx.subjects.PublishSubject;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ShankAcceptanceTest {

    @Before
    public void setUp() {
        Shank.clearAll();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void provideNewInstanceProvides_whenObjectHasFactoryWithNoArgs_provideNewInstanceEveryTime() {
        Shank.registerFactory(ArrayList.class, () -> new ArrayList<>());

        final Object providedNewInstance = Shank.provideNewInstance(FooObject.class);
        final Object otherProvidedNewInstance = Shank.provideNewInstance(FooObject.class);

        assertTrue(providedNewInstance != null);
        assertTrue(otherProvidedNewInstance != null);
        assertTrue(providedNewInstance instanceof FooObject);
        assertTrue(otherProvidedNewInstance instanceof FooObject);
        assertTrue(providedNewInstance != otherProvidedNewInstance);
    }

    @Test
    public void provideNewInstanceProvides_whenObjectHasFactoryWith_1_Args_provideNewInstanceEveryTime() {
        Shank.registerFactory(List.class, Collections::singletonList);

        final Object providedNewInstance = Shank.provideNewInstance(List.class, "a");
        final Object otherProvidedNewInstance = Shank.provideNewInstance(List.class, "a");

        assertTrue(providedNewInstance != null);
        assertTrue(otherProvidedNewInstance != null);
        assertTrue(providedNewInstance instanceof List);
        assertTrue(otherProvidedNewInstance instanceof List);
        assertTrue(providedNewInstance != otherProvidedNewInstance);
    }


    @Test
    public void provideNewInstanceProvides_whenObjectHasFactoryWith_2_Args_provideNewInstanceEveryTime() {
        Shank.registerFactory(List.class, (a, b) -> asList(a, b));

        final Object providedNewInstance = Shank.provideNewInstance(List.class, "a", "b");
        final Object otherProvidedNewInstance = Shank.provideNewInstance(List.class, "a", "b");

        assertTrue(providedNewInstance != null);
        assertTrue(otherProvidedNewInstance != null);
        assertTrue(providedNewInstance instanceof List);
        assertTrue(otherProvidedNewInstance instanceof List);
        assertTrue(providedNewInstance != otherProvidedNewInstance);
    }

    @Test
    public void provideNewInstanceProvides_whenObjectHasFactoryWith_3_Args_provideNewInstanceEveryTime() {
        Shank.registerFactory(List.class, (a, b, c) -> asList(a, b, c));

        final Object providedNewInstance = Shank.provideNewInstance(List.class, "a", "b", "c");
        final Object otherProvidedNewInstance = Shank.provideNewInstance(List.class, "a", "b", "c");

        assertTrue(providedNewInstance != null);
        assertTrue(otherProvidedNewInstance != null);
        assertTrue(providedNewInstance instanceof List);
        assertTrue(otherProvidedNewInstance instanceof List);
        assertTrue(providedNewInstance != otherProvidedNewInstance);
    }

    @Test
    public void provideNewInstanceProvides_whenObjectHasFactoryWith_4_Args_provideNewInstanceEveryTime() {
        Shank.registerFactory(List.class, (a, b, c, d) -> asList(a, b, c, d));

        final Object providedNewInstance = Shank.provideNewInstance(List.class, "a", "b", "c", "d");
        final Object otherProvidedNewInstance = Shank.provideNewInstance(List.class, "a", "b", "c", "d");

        assertTrue(providedNewInstance != null);
        assertTrue(otherProvidedNewInstance != null);
        assertTrue(providedNewInstance instanceof List);
        assertTrue(otherProvidedNewInstance instanceof List);
        assertTrue(providedNewInstance != otherProvidedNewInstance);
    }

    @Test
    public void provideNamedNewInstanceInstanceProvides_whenObjectHasFactoryWithNoArgs_provideNamedNewInstanceInstanceEveryTime() {
        Shank.registerNamedFactory(List.class, "one", () -> new ArrayList<>());
        Shank.registerNamedFactory(List.class, "two", () -> new LinkedList<>());

        final Object firstProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one");
        final Object secondProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one");

        final Object firstOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two");
        final Object secondOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two");

        assertTrue(firstProvidedNewInstance != null);
        assertTrue(firstOtherProvidedNewInstance != null);
        assertTrue(secondProvidedNewInstance != null);
        assertTrue(secondOtherProvidedNewInstance != null);
        assertTrue(firstProvidedNewInstance instanceof ArrayList);
        assertTrue(firstOtherProvidedNewInstance instanceof LinkedList);
        assertTrue(secondProvidedNewInstance instanceof ArrayList);
        assertTrue(secondOtherProvidedNewInstance instanceof LinkedList);

        assertTrue(firstProvidedNewInstance != secondProvidedNewInstance);
        assertTrue(firstOtherProvidedNewInstance != secondOtherProvidedNewInstance);
    }

    @Test
    public void provideNamedNewInstanceInstanceProvides_whenObjectHasFactoryWith_1_Args_provideNamedNewInstanceInstanceEveryTime() {
        Shank.registerNamedFactory(List.class, "one", (String a) -> new ArrayList<String>(){{add(a);}});
        Shank.registerNamedFactory(List.class, "two", (String a) -> new LinkedList<String>(){{add(a);}});

        final Object firstProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one", "a");
        final Object secondProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one", "a");

        final Object firstOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two", "a");
        final Object secondOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two", "a");

        assertTrue(firstProvidedNewInstance != null);
        assertTrue(firstOtherProvidedNewInstance != null);
        assertTrue(secondProvidedNewInstance != null);
        assertTrue(secondOtherProvidedNewInstance != null);
        assertTrue(firstProvidedNewInstance instanceof ArrayList);
        assertTrue(firstOtherProvidedNewInstance instanceof LinkedList);
        assertTrue(secondProvidedNewInstance instanceof ArrayList);
        assertTrue(secondOtherProvidedNewInstance instanceof LinkedList);

        assertTrue(firstProvidedNewInstance != secondProvidedNewInstance);
        assertTrue(firstOtherProvidedNewInstance != secondOtherProvidedNewInstance);
    }

    @Test
    public void provideNamedNewInstanceInstanceProvides_whenObjectHasFactoryWith_2_Args_provideNamedNewInstanceInstanceEveryTime() {
        Shank.registerNamedFactory(List.class, "one", (String a, String b) -> new ArrayList<String>(){{add(a); add(b);}});
        Shank.registerNamedFactory(List.class, "two", (String a, String b) -> new LinkedList<String>(){{add(a); add(b);}});

        final Object firstProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one", "a", "b");
        final Object secondProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one", "a", "b");

        final Object firstOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two", "a", "b");
        final Object secondOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two", "a", "b");

        assertTrue(firstProvidedNewInstance != null);
        assertTrue(firstOtherProvidedNewInstance != null);
        assertTrue(secondProvidedNewInstance != null);
        assertTrue(secondOtherProvidedNewInstance != null);
        assertTrue(firstProvidedNewInstance instanceof ArrayList);
        assertTrue(firstOtherProvidedNewInstance instanceof LinkedList);
        assertTrue(secondProvidedNewInstance instanceof ArrayList);
        assertTrue(secondOtherProvidedNewInstance instanceof LinkedList);

        assertTrue(firstProvidedNewInstance != secondProvidedNewInstance);
        assertTrue(firstOtherProvidedNewInstance != secondOtherProvidedNewInstance);
    }

    @Test
    public void provideNamedNewInstanceInstanceProvides_whenObjectHasFactoryWith_3_Args_provideNamedNewInstanceInstanceEveryTime() {
        Shank.registerNamedFactory(List.class, "one", (String a, String b, String c) -> new ArrayList<String>(){{add(a); add(b); add(c);}});
        Shank.registerNamedFactory(List.class, "two", (String a, String b, String c) -> new LinkedList<String>(){{add(a); add(b); add(c);}});

        final Object firstProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one", "a", "b", "c");
        final Object secondProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one", "a", "b", "c");

        final Object firstOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two", "a", "b", "c");
        final Object secondOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two", "a", "b", "c");

        assertTrue(firstProvidedNewInstance != null);
        assertTrue(firstOtherProvidedNewInstance != null);
        assertTrue(secondProvidedNewInstance != null);
        assertTrue(secondOtherProvidedNewInstance != null);
        assertTrue(firstProvidedNewInstance instanceof ArrayList);
        assertTrue(firstOtherProvidedNewInstance instanceof LinkedList);
        assertTrue(secondProvidedNewInstance instanceof ArrayList);
        assertTrue(secondOtherProvidedNewInstance instanceof LinkedList);

        assertTrue(firstProvidedNewInstance != secondProvidedNewInstance);
        assertTrue(firstOtherProvidedNewInstance != secondOtherProvidedNewInstance);
    }

    @Test
    public void provideNamedNewInstanceInstanceProvides_whenObjectHasFactoryWith_4_Args_provideNamedNewInstanceInstanceEveryTime() {
        Shank.registerNamedFactory(List.class, "one", (String a, String b, String c, String d) -> new ArrayList<String>(){{add(a); add(b); add(c); add(d);}});
        Shank.registerNamedFactory(List.class, "two", (String a, String b, String c, String d) -> new LinkedList<String>(){{add(a); add(b); add(c); add(d);}});

        final Object firstProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one", "a", "b", "c", "d");
        final Object secondProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "one", "a", "b", "c", "d");

        final Object firstOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two", "a", "b", "c", "d");
        final Object secondOtherProvidedNewInstance = Shank.provideNamedNewInstance(List.class, "two", "a", "b", "c", "d");

        assertTrue(firstProvidedNewInstance != null);
        assertTrue(firstOtherProvidedNewInstance != null);
        assertTrue(secondProvidedNewInstance != null);
        assertTrue(secondOtherProvidedNewInstance != null);
        assertTrue(firstProvidedNewInstance instanceof ArrayList);
        assertTrue(firstOtherProvidedNewInstance instanceof LinkedList);
        assertTrue(secondProvidedNewInstance instanceof ArrayList);
        assertTrue(secondOtherProvidedNewInstance instanceof LinkedList);

        assertTrue(firstProvidedNewInstance != secondProvidedNewInstance);
        assertTrue(firstOtherProvidedNewInstance != secondOtherProvidedNewInstance);
    }

    // Singletons

    @Test
    public void provideSingleton_whenObjectHasFactory_provideSingletonsRightObject() {
        Shank.registerFactory(FooObject.class, FooObject::new);

        final Object provideSingletond = Shank.provideSingleton(FooObject.class);

        assertTrue(provideSingletond != null);
        assertTrue(provideSingletond instanceof FooObject);
    }

    @Test
    public void provideSingleton_whenObjectHasNoFactory_throwsMeaninfulErrro() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("No factory with String, String, String arguments registered for List");

        Shank.registerFactory(List.class, (a, b) -> asList("a", "b"));
        Shank.provideSingleton(List.class, "a", "b", "c");
    }

    @Test
    public void provideSingleton_whenObjectHasFactoryWith1Parameter_provideSingletonsRightObject() {
        Shank.registerFactory(List.class, Collections::singletonList);

        final List provideSingletond = Shank.provideSingleton(List.class, "a");

        assertTrue(provideSingletond != null);
        assertThat(provideSingletond, is(Collections.singletonList("a")));
    }

    @Test
    public void provideSingleton_whenObjectHasFactoryWith2Parameter_provideSingletonsRightObject() {
        Shank.registerFactory(List.class, (String a, String b) -> asList(a, b));

        final List provideSingletond = Shank.provideSingleton(List.class, "a", "b");

        assertTrue(provideSingletond != null);
        assertThat(provideSingletond, is(asList("a", "b")));
    }

    @Test
    public void provideSingleton_whenObjectHasFactoryWith3Parameter_provideSingletonsRightObject() {
        Shank.registerFactory(List.class, (a, b, c) -> asList(a, b, c));

        final List provideSingletond = Shank.provideSingleton(List.class, "a", "b", "c");

        assertTrue(provideSingletond != null);
        assertThat(provideSingletond, is(asList("a", "b", "c")));
    }

    @Test
    public void provideSingleton_whenObjectHasFactoryWith4Parameter_provideSingletonsRightObject() {
        Shank.registerFactory(List.class, (a, b, c, d) -> asList(a, b, c, d));

        final List provideSingletond = Shank.provideSingleton(List.class, "a", "b", "c", "d");

        assertTrue(provideSingletond != null);
        assertThat(provideSingletond, is(asList("a", "b", "c", "d")));
    }

    @Test
    public void provideSingleton_whenObjectHasFactoryWithParametersOfDifferentType_provideSingletonsRightObject() {
        Shank.registerFactory(ClassWithConstructorParameters.class, (String aString, Integer anInteger) ->
                new ClassWithConstructorParameters(aString, anInteger));

        final ClassWithConstructorParameters provideSingletond = Shank.provideSingleton(ClassWithConstructorParameters.class, "a", 1);

        assertTrue(provideSingletond != null);
        assertThat(provideSingletond.a, is("a"));
        assertThat(provideSingletond.b, is(1));
    }

    @Test
    public void provideSingleton_whenObjectHasNamedFactoryWith0Arguments_provideSingletonsRightObject() {
        Shank.registerNamedFactory(FooObject.class, "first", ChildFooObject::new);
        Shank.registerNamedFactory(FooObject.class, "second", OtherChildFooObject::new);

        final Object first = Shank.provideSingletonNamed(FooObject.class, "first");
        final Object second = Shank.provideSingletonNamed(FooObject.class, "second");

        assertTrue(first != null);
        assertTrue(second != null);
        assertTrue(second != first);
        assertTrue(first instanceof ChildFooObject);
        assertTrue(second instanceof OtherChildFooObject);
    }

    @Test
    public void provideSingleton_whenObjectHasNamedFactoryWith1Arguments_provideSingletonsRightObject() {
        Shank.registerNamedFactory(List.class, "name", (a) -> asList("a"));

        final List provideSingletond = Shank.provideSingletonNamed(List.class, "name", "a");
        assertThat(provideSingletond, is(Collections.singletonList("a")));
    }

    @Test
    public void provideSingleton_whenObjectHasNamedFactoryWith2Arguments_provideSingletonsRightObject() {
        Shank.registerNamedFactory(List.class, "name", (a, b) -> asList(a, b));

        final List provideSingletond = Shank.provideSingletonNamed(List.class, "name", "a", "b");
        assertThat(provideSingletond, is(asList("a", "b")));
    }

    @Test
    public void provideSingleton_whenObjectHasNamedFactoryWith3Arguments_provideSingletonsRightObject() {
        Shank.registerNamedFactory(List.class, "name", (a, b, c) -> asList(a, b, c));

        final List provideSingletond = Shank.provideSingletonNamed(List.class, "name", "a", "b", "c");
        assertThat(provideSingletond, is(asList("a", "b", "c")));
    }

    @Test
    public void provideSingleton_whenObjectHasNamedFactoryWith4Arguments_provideSingletonsRightObject() {
        Shank.registerNamedFactory(List.class, "name", (a, b, c, d) -> asList(a, b, c, d));

        final List provideSingletond = Shank.provideSingletonNamed(List.class, "name", "a", "b", "c", "d");
        assertThat(provideSingletond, is(asList("a", "b", "c", "d")));
    }

    @Test
    public void provideSingleton_whenObjectHasFactory_provideSingletonsPolymorphicObject() {
        Shank.registerFactory(FooObject.class, ChildFooObject::new);

        final FooObject provideSingletond = Shank.provideSingleton(FooObject.class);

        assertTrue(provideSingletond != null);
        assertTrue(provideSingletond instanceof ChildFooObject);
    }

    @Test
    public void provideSingleton_whenObjectHasFactory_and_provideSingletonIsCalledMultipleTimes_provideSingletonsSameInstance() {
        Shank.registerFactory(FooObject.class, FooObject::new);

        final FooObject provideSingletond = Shank.provideSingleton(FooObject.class);
        final FooObject secondProvided = Shank.provideSingleton(FooObject.class);

        assertTrue(provideSingletond == secondProvided);
    }

    @Test
    public void clear_whenObjectIsRemoved_provideSingletonsNewInstance() {
        Shank.registerFactory(FooObject.class, FooObject::new);

        final FooObject provideSingletond = Shank.provideSingleton(FooObject.class);

        Shank.clearUnscoped(FooObject.class);

        final FooObject secondProvided = Shank.provideSingleton(FooObject.class);

        assertTrue(provideSingletond != secondProvided);
    }

    // Legacy provide

    @Test
    public void provide_whenObjectHasFactory_providesRightObject() {
        Shank.registerFactory(FooObject.class, FooObject::new);

        final Object provided = Shank.provide(FooObject.class);

        assertTrue(provided != null);
        assertTrue(provided instanceof FooObject);
    }

    @Test
    public void provide_whenObjectHasNoFactory_throwsMeaninfulErrro() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("No factory with String, String, String arguments registered for List");

        Shank.registerFactory(List.class, (a, b) -> asList("a", "b"));
        Shank.provide(List.class, "a", "b", "c");
    }

    @Test
    public void provide_whenObjectHasFactoryWith1Parameter_providesRightObject() {
        Shank.registerFactory(List.class, Collections::singletonList);

        final List provided = Shank.provide(List.class, "a");

        assertTrue(provided != null);
        assertThat(provided, is(Collections.singletonList("a")));
    }

    @Test
    public void provide_whenObjectHasFactoryWith2Parameter_providesRightObject() {
        Shank.registerFactory(List.class, (String a, String b) -> asList(a, b));

        final List provided = Shank.provide(List.class, "a", "b");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a", "b")));
    }

    @Test
    public void provide_whenObjectHasFactoryWith3Parameter_providesRightObject() {
        Shank.registerFactory(List.class, (a, b, c) -> asList(a, b, c));

        final List provided = Shank.provide(List.class, "a", "b", "c");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a", "b", "c")));
    }

    @Test
    public void provide_whenObjectHasFactoryWith4Parameter_providesRightObject() {
        Shank.registerFactory(List.class, (a, b, c, d) -> asList(a, b, c, d));

        final List provided = Shank.provide(List.class, "a", "b", "c", "d");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a", "b", "c", "d")));
    }

    @Test
    public void provide_whenObjectHasFactoryWithParametersOfDifferentType_providesRightObject() {
        Shank.registerFactory(ClassWithConstructorParameters.class, (String aString, Integer anInteger) ->
                new ClassWithConstructorParameters(aString, anInteger));

        final ClassWithConstructorParameters provided = Shank.provide(ClassWithConstructorParameters.class, "a", 1);

        assertTrue(provided != null);
        assertThat(provided.a, is("a"));
        assertThat(provided.b, is(1));
    }

    @Test
    public void provide_whenObjectHasNamedFactoryWith0Arguments_providesRightObject() {
        Shank.registerNamedFactory(FooObject.class, "first", ChildFooObject::new);
        Shank.registerNamedFactory(FooObject.class, "second", OtherChildFooObject::new);

        final Object first = Shank.provideNamed(FooObject.class, "first");
        final Object second = Shank.provideNamed(FooObject.class, "second");

        assertTrue(first != null);
        assertTrue(second != null);
        assertTrue(second != first);
        assertTrue(first instanceof ChildFooObject);
        assertTrue(second instanceof OtherChildFooObject);
    }

    @Test
    public void provide_whenObjectHasNamedFactoryWith1Arguments_providesRightObject() {
        Shank.registerNamedFactory(List.class, "name", (a) -> asList("a"));

        final List provided = Shank.provideNamed(List.class, "name", "a");
        assertThat(provided, is(Collections.singletonList("a")));
    }

    @Test
    public void provide_whenObjectHasNamedFactoryWith2Arguments_providesRightObject() {
        Shank.registerNamedFactory(List.class, "name", (a, b) -> asList(a, b));

        final List provided = Shank.provideNamed(List.class, "name", "a", "b");
        assertThat(provided, is(asList("a", "b")));
    }

    @Test
    public void provide_whenObjectHasNamedFactoryWith3Arguments_providesRightObject() {
        Shank.registerNamedFactory(List.class, "name", (a, b, c) -> asList(a, b, c));

        final List provided = Shank.provideNamed(List.class, "name", "a", "b", "c");
        assertThat(provided, is(asList("a", "b", "c")));
    }

    @Test
    public void provide_whenObjectHasNamedFactoryWith4Arguments_providesRightObject() {
        Shank.registerNamedFactory(List.class, "name", (a, b, c, d) -> asList(a, b, c, d));

        final List provided = Shank.provideNamed(List.class, "name", "a", "b", "c", "d");
        assertThat(provided, is(asList("a", "b", "c", "d")));
    }

    @Test
    public void provide_whenObjectHasFactory_providesPolymorphicObject() {
        Shank.registerFactory(FooObject.class, ChildFooObject::new);

        final FooObject provided = Shank.provide(FooObject.class);

        assertTrue(provided != null);
        assertTrue(provided instanceof ChildFooObject);
    }

    @Test
    public void provide_whenObjectHasFactory_and_provideIsCalledMultipleTimes_providesSameInstance() {
        Shank.registerFactory(FooObject.class, FooObject::new);

        final FooObject provided = Shank.provide(FooObject.class);
        final FooObject secondProvided = Shank.provide(FooObject.class);

        assertTrue(provided == secondProvided);
    }

    @Test
    public void clear_whenObjectIsRemoved_providesNewInstance() {
        Shank.registerFactory(FooObject.class, FooObject::new);

        final FooObject provided = Shank.provide(FooObject.class);

        Shank.clearUnscoped(FooObject.class);

        final FooObject secondProvided = Shank.provide(FooObject.class);

        assertTrue(provided != secondProvided);
    }

    @Test
    public void withScope_and_0arguments_returnsObjectForScope() {
        Shank.registerFactory(FooObject.class, FooObject::new);

        final FooObject provided = Shank.withScope(OtherFooObject.class).provide(FooObject.class);

        assertTrue(provided != null);
    }

    @Test
    public void withScope_and_1_arguments_returnsObjectForScope() {
        Shank.registerFactory(List.class, (a) -> asList(a));

        final List provided = Shank.withScope(OtherFooObject.class).provide(List.class, "a");

        assertTrue(provided != null);
        assertThat(provided, is(Collections.singletonList("a")));
    }

    @Test
    public void withScope_and_2_arguments_returnsObjectForScope() {
        Shank.registerFactory(List.class, (a, b) -> asList(a, b));

        final List provided = Shank.withScope(OtherFooObject.class).provide(List.class, "a", "b");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a", "b")));
    }

    @Test
    public void withScope_and_3_arguments_returnsObjectForScope() {
        Shank.registerFactory(List.class, (a, b, c) -> asList(a, b, c));

        final List provided = Shank.withScope(OtherFooObject.class).provide(List.class, "a", "b", "c");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a", "b", "c")));
    }

    @Test
    public void withScope_and_4_arguments_returnsObjectForScope() {
        Shank.registerFactory(List.class, (a, b, c, d) -> asList(a, b, c, d));

        final List provided = Shank.withScope(OtherFooObject.class).provide(List.class, "a", "b", "c", "d");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a", "b", "c", "d")));
    }

    @Test
    public void withScope_whenCalledMultipleTimes_returnsSameObjectForScope() {
        Shank.registerFactory(FooObject.class, FooObject::new);

        final FooObject provided = Shank.withScope(OtherFooObject.class).provide(FooObject.class);
        final FooObject otherProvided = Shank.withScope(OtherFooObject.class).provide(FooObject.class);

        assertTrue(provided == otherProvided);
    }

    @Test
    public void withObjectScope_whenCalledMultipleTimes_returnsSameObjectForScope() {
        Shank.registerFactory(FooObject.class, FooObject::new);

        final FooObject provided = Shank.withScope("foo").provide(FooObject.class);
        final FooObject otherProvided = Shank.withScope("foo").provide(FooObject.class);

        assertTrue(provided == otherProvided);
    }

    @Test
    public void withScope_andWithClearObservable_whenCalledMultipleTimes_returnsDifferentObjectForScope() {
        Shank.registerFactory(FooObject.class, FooObject::new);
        PublishSubject<Object> remove = PublishSubject.create();

        final FooObject provided = Shank.withBoundScope(OtherFooObject.class, remove).provide(FooObject.class);
        remove.onNext(new Object());
        final FooObject otherProvided = Shank.withScope(OtherFooObject.class).provide(FooObject.class);

        assertTrue(provided != otherProvided);
        assertNotNull(provided);
        assertNotNull(otherProvided);
    }

    @Test
    public void withScope_whenHasNamedFactory_returnsObjectForNameAndScope_with_0_arguments() {
        Shank.registerNamedFactory(FooObject.class, "first", ChildFooObject::new);
        Shank.registerNamedFactory(FooObject.class, "second", OtherChildFooObject::new);

        FooObject first = Shank.withScope(OtherFooObject.class).provideNamed(FooObject.class, "first");
        FooObject second = Shank.withScope(OtherFooObject.class).provideNamed(FooObject.class, "second");

        assertTrue(first != null);
        assertTrue(second != null);
        assertTrue(second != first);
        assertTrue(first instanceof ChildFooObject);
        assertTrue(second instanceof OtherChildFooObject);
    }

    @Test
    public void withScope_whenHasNamedFactory_returnsObjectForNameAndScope_with_1_arguments() {
        Shank.registerNamedFactory(List.class, "list", (a) -> asList(a));
        List provided = Shank.withScope(List.class).provideNamed(List.class, "list", "a");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a")));
    }

    @Test
    public void withScope_whenHasNamedFactory_returnsObjectForNameAndScope_with_2_arguments() {
        Shank.registerNamedFactory(List.class, "list", (a, b) -> asList(a, b));
        List provided = Shank.withScope(List.class).provideNamed(List.class, "list", "a", "b");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a", "b")));
    }

    @Test
    public void withScope_whenHasNamedFactory_returnsObjectForNameAndScope_with_3_arguments() {
        Shank.registerNamedFactory(List.class, "list", (a, b, c) -> asList(a, b, c));
        List provided = Shank.withScope(List.class).provideNamed(List.class, "list", "a", "b", "c");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a", "b", "c")));
    }

    @Test
    public void withScope_whenHasNamedFactory_returnsObjectForNameAndScope_with_4_arguments() {
        Shank.registerNamedFactory(List.class, "list", (a, b, c, d) -> asList(a, b, c, d));
        List provided = Shank.withScope(List.class).provideNamed(List.class, "list", "a", "b", "c", "d");

        assertTrue(provided != null);
        assertThat(provided, is(asList("a", "b", "c", "d")));
    }

    @Test
    public void withBoundScope_whenHasNamedFactory_andScopeIsCleared_returnsDifferentObjects() {
        Shank.registerNamedFactory(FooObject.class, "object", FooObject::new);

        PublishSubject<Object> remove = PublishSubject.create();

        FooObject first = Shank.withBoundScope(OtherFooObject.class, remove).provideNamed(FooObject.class, "object");
        remove.onNext(null);
        FooObject other = Shank.withBoundScope(OtherFooObject.class, remove).provideNamed(FooObject.class, "object");

        assertTrue(first != null);
        assertTrue(other != null);
        assertTrue(other != first);
    }

    @Test
    public void withBoundObjectScope_whenHasNamedFactory_andScopeIsCleared_returnsDifferentObjects() {
        Shank.registerNamedFactory(FooObject.class, "object", FooObject::new);

        PublishSubject<Object> remove = PublishSubject.create();

        FooObject first = Shank.withBoundScope("foo", remove).provideNamed(FooObject.class, "object");
        remove.onNext(null);
        FooObject other = Shank.withBoundScope("foo", remove).provideNamed(FooObject.class, "object");

        assertTrue(first != null);
        assertTrue(other != null);
        assertTrue(other != first);
    }

    @Test
    public void withScope_whenHasNamedFactory__calledMultipleTimes_returnsSameObjectForNameAndScope() {
        Shank.registerNamedFactory(FooObject.class, "first", ChildFooObject::new);
        Shank.registerNamedFactory(FooObject.class, "second", OtherChildFooObject::new);

        FooObject first = Shank.withScope(OtherFooObject.class).provideNamed(FooObject.class, "first");
        FooObject second = Shank.withScope(OtherFooObject.class).provideNamed(FooObject.class, "second");

        FooObject otherFirst = Shank.withScope(OtherFooObject.class).provideNamed(FooObject.class, "first");
        FooObject otherSecond = Shank.withScope(OtherFooObject.class).provideNamed(FooObject.class, "second");

        assertThat(first, is(otherFirst));
        assertThat(second, is(otherSecond));
    }

    @Test(expected = NoFactoryException.class)
    public void provide_whenObjectHasNoFactory_throwsException() throws Exception {
        Shank.provide(OtherFooObject.class);
    }

    static class FooObject {

        FooObject() {
        }

    }

    static class ClassWithConstructorParameters {

        private String a;
        private Integer b;

        ClassWithConstructorParameters(String a, Integer b) {
            this.a = a;
            this.b = b;
        }
    }

    static class ChildFooObject extends FooObject {
    }

    static class OtherChildFooObject extends FooObject {
    }

    static class OtherFooObject {
    }
}