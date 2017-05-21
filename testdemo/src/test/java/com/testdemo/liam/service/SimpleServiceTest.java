package com.testdemo.liam.service;


import com.google.common.collect.Lists;
import com.testdemo.liam.po.Param;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by liam on 2016/12/19.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SimpleService.class)
public class SimpleServiceTest {

  @Test
  public void testCallStaticMethod() {
    PowerMockito.mockStatic(SimpleService.class);
    Mockito.when(SimpleService.staticMethod()).thenReturn(2L);
    Param p = new Param().setCount(0L);
    Mockito.when(SimpleService.callStaticMethod(Lists.newArrayList(p))).thenCallRealMethod();
    Long result = SimpleService.callStaticMethod(Lists.newArrayList(p));
    Assert.assertThat(result, equalTo(2L));
  }

  @Test
  public void testCallPrivateMethod() throws Exception {
    SimpleService spy = PowerMockito.spy(new SimpleService());
    PowerMockito.doReturn(2).when(spy, "privateMethod");
    int i = spy.callPrivateMethod();
    Assert.assertThat(i, equalTo(1));
  }


  @Test
  public void testGetListWithMock() throws Exception {
    SimpleService mock = Mockito.mock(SimpleService.class);
    Mockito.when(mock.mockMethod()).thenReturn(Lists.newArrayList(2));
    Mockito.when(mock.callMockMethod()).thenCallRealMethod();

    List<Integer> list = mock.callMockMethod();
    Mockito.verify(mock).mockMethod();
    Assert.assertArrayEquals(new Object[] {true, 1, 1},
        new Object[] {list != null, list.size(), list.get(0)});
  }


  @Test
  public void testGetListWithSpyWhenThen() throws Exception {

    SimpleService spy = Mockito.spy(new SimpleService());
    Mockito.when(spy.mockMethod()).thenReturn(Lists.newArrayList(2));

    List<Integer> list = spy.callMockMethod();
    Mockito.verify(spy).mockMethod();
    Assert.assertArrayEquals(new Object[] {true, 1, 1},
            new Object[] {list != null, list.size(), list.get(0)});
  }

  @Test
  public void testGetListWithSpyDoWhen() throws Exception {
    SimpleService spy = Mockito.spy(new SimpleService());
    Mockito.doReturn(Lists.newArrayList(2)).when(spy).mockMethod();

    List<Integer> list = spy.callMockMethod();
    Assert.assertArrayEquals(new Object[] {true, 1, 1},
            new Object[] {list != null, list.size(), list.get(0)});
  }


  @Test
  public void usageOfVerify() {
    ArrayList<Integer> spyList1 = Mockito.spy(Lists.<Integer>newArrayList());
    spyList1.add(1);
    spyList1.add(1);
    spyList1.add(2);

    Mockito.verify(spyList1).add(2);
    Mockito.verify(spyList1, Mockito.timeout(1000)).add(2);
    Mockito.verify(spyList1, Mockito.times(2)).add(1);
    Mockito.verify(spyList1, Mockito.atLeast(2)).add(1);
    Mockito.verify(spyList1, Mockito.atLeastOnce()).add(1);
    Mockito.verify(spyList1, Mockito.atMost(2)).add(1);
    Mockito.verify(spyList1, Mockito.never()).add(100);

    InOrder inOrder = Mockito.inOrder(spyList1);
    inOrder.verify(spyList1, Mockito.times(2)).add(1);
    inOrder.verify(spyList1).add(2);
    Mockito.verifyNoMoreInteractions(spyList1);

    ArrayList<Integer> spyList2 = Mockito.spy(Lists.<Integer>newArrayList());
    Mockito.verifyZeroInteractions(spyList2);

    Param param = new Param();
    param.setCount(1L);
    param.setName("param");
    List<Param> spyParamList = Mockito.spy(Lists.<Param>newArrayList());
    spyParamList.add(param);

    Param expectedParam = new Param();
    expectedParam.setCount(1L);
    expectedParam.setName("param");
    Mockito.verify(spyParamList).add(expectedParam);

  }



  @Test
  public void usageOfAssertThat() {
    Assert.assertThat("result", containsString("res"));
    Assert.assertThat("result", equalTo("result"));
    Assert.assertThat("result", is("result"));
    Assert.assertThat("result", allOf(containsString("sult"), containsString("res")));
    Assert.assertThat("result", anyOf(equalTo("result"), equalTo("response")));
    Assert.assertThat("result", startsWith("res"));
    Assert.assertThat("result", endsWith("sult"));
    Assert.assertThat("result", either(startsWith("res")).or(equalTo("result")));
    Assert.assertThat("result", both(startsWith("res")).and(equalTo("result")));
    Assert.assertThat(Lists.newArrayList("a", "b", "c"), hasItem("a"));
    Assert.assertThat(Lists.newArrayList("a", "b", "c"), hasItem(equalTo("a")));
    Assert.assertThat(Lists.newArrayList("a", "b", "c"), hasItems("a", "b"));
    Assert.assertThat(Lists.newArrayList("a", "b", "c"), hasItems(equalTo("a"), equalTo("b")));
    Assert.assertThat(Lists.newArrayList("ac", "bc", "ca"), everyItem(containsString("c")));
    Assert.assertThat(2L, instanceOf(Long.class));
    Assert.assertThat(2L, isA(Long.class));


  }

  @Test(expected = IllegalAccessException.class, timeout = 10000)
  public void testThrowException() {
    // do test
  }

}
