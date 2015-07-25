package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.janino.SimpleCompiler;

import com.hrv.component.utils.sourcecode.ClassReturnType;
import com.hrv.component.utils.sourcecode.Modifier;
import com.hrv.component.utils.sourcecode.VoidReturnType;
import com.hrv.component.utils.sourcecode.builder.ClassBuilder;
import com.hrv.component.utils.sourcecode.builder.ClassInitMethodBuilder;
import com.hrv.component.utils.sourcecode.builder.CommonMethodBuilder;
import com.hrv.component.utils.sourcecode.builder.VariableBuilder;
import com.hrv.component.utils.sourcecode.wrapper.ClassWrapper;
import com.hrv.component.utils.sourcecode.wrapper.CommonMethodWrapper;

public class ClassBuilderTest {
	private static final Logger logger = Logger.getLogger(ClassBuilderTest.class);

	public void test1() {
		try {
			List<ClassWrapper> list = new ArrayList<ClassWrapper>();

			ClassWrapper class1 = new ClassBuilder();
			class1.setName("Class1");
			class1.setPackage("com.bla.bla");
			class1.addModifier(Modifier.PUBLIC);
			class1.setExtend("test.ClassTest");
			class1.addVariable((VariableBuilder) new VariableBuilder("String", "var1", null).addModifier(Modifier.PRIVATE));
			class1.addVariable((VariableBuilder) new VariableBuilder("String", "var2", null).addModifier(Modifier.PRIVATE));
			class1.addVariable((VariableBuilder) new VariableBuilder("String", "obj1", null).addModifier(Modifier.PRIVATE).addModifier(Modifier.STATIC));

			list.add(class1);

			ClassInitMethodBuilder cimb = new ClassInitMethodBuilder(class1);
			cimb.addLineCode("init1()");
			cimb.addLineCode("obj1 = \"oke deh\"");

			CommonMethodWrapper methodClass1 = new CommonMethodBuilder(class1);
			methodClass1.addModifier(Modifier.PUBLIC);
			methodClass1.setReturnType(new ClassReturnType("String"));
			methodClass1.setName("methodTest1");
			methodClass1.addParameter(String.class.getName(), "obj1");
			methodClass1.addParameter(String.class.getName(), "obj2");
			methodClass1.addLineCode("obj1 = \"okedeh1\"");
			methodClass1.addLineCode("obj2 = \"okedeh2\"");
			methodClass1.addLineCode("return obj1");

			methodClass1 = new CommonMethodBuilder(class1);
			methodClass1.addModifier(Modifier.PUBLIC);
			methodClass1.setReturnType(new VoidReturnType());
			methodClass1.setName("methodTest2");
			methodClass1.addParameter(String.class.getName(), "obj1");
			methodClass1.addParameter(String.class.getName(), "obj2");
			methodClass1.addLineCode("obj1 = \"okedeh1\"");
			methodClass1.addLineCode("obj2 = \"okedeh2\"");

			methodClass1 = (CommonMethodBuilder) new CommonMethodBuilder(class1).addModifier(Modifier.STATIC);
			methodClass1.addModifier(Modifier.PRIVATE);
			methodClass1.setName("init1");
			methodClass1.setReturnType(new VoidReturnType());

			// inner - start
			ClassWrapper innerClass1 = new ClassBuilder();
			class1.addInnerClass(innerClass1);
			innerClass1.setName("InnerClass");

			CommonMethodWrapper methodInnerClass1 = new CommonMethodBuilder(innerClass1);
			methodInnerClass1.setName("innerMethod");
			methodInnerClass1.setReturnType(new VoidReturnType());
			methodInnerClass1.addLineCode("////");

			ClassWrapper innerClass2 = new ClassBuilder();
			innerClass2.setName("InnerClass2");
			innerClass1.addInnerClass(innerClass2);

			ClassWrapper innerClass2Class1 = new ClassBuilder();
			innerClass2Class1.setName("InnerClass3");
			innerClass2.addInnerClass(innerClass2Class1);

			CommonMethodWrapper methodInnerClass2Class1 = new CommonMethodBuilder(innerClass2Class1);
			methodInnerClass2Class1.setName("innerMethod1");
			methodInnerClass2Class1.setReturnType(new VoidReturnType());
			methodInnerClass2Class1.addParameter(String.class.getName(), "obj1");
			methodInnerClass2Class1.addParameter(String.class.getName(), "obj2");
			methodInnerClass2Class1.addLineCode("obj1 = \"okedeh1\"");
			methodInnerClass2Class1.addLineCode("obj2 = \"okedeh2\"");
			// inner - end

			logger.info(class1.create());

			SimpleCompiler sc = new SimpleCompiler();
			sc.cook(class1.toString());

			Class c = Class.forName(class1.getClassName(), true, sc.getClassLoader());

			logger.info(c);

			Class[] param = { String.class, String.class };
			Method m = c.getMethod("methodTest1", param);
			logger.info(m);

			Object[] params = { "test1", "test2" };
			logger.info(m.invoke(c.newInstance(), params));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test2() {
		try {
			ClassWrapper cb = new ClassBuilder();

			cb.setName("ClassTest2");
			cb.addInterface("test.ClassTestInterface");
			cb.addModifier(Modifier.PUBLIC);
			cb.setPackage("test");

			CommonMethodWrapper methodClass1 = new CommonMethodBuilder(cb);
			methodClass1.addModifier(Modifier.PUBLIC);
			methodClass1.setReturnType(new ClassReturnType("String"));
			methodClass1.setName("getValue1");
			methodClass1.addParameter(String.class.getName(), "obj1");
			methodClass1.addLineCode("obj1 = \"okedeh1 \" + obj1");
			methodClass1.addLineCode("return obj1");

			logger.info(cb.create());

			SimpleCompiler sc = new SimpleCompiler();
			sc.cook(cb.toString());

			Class c = Class.forName(cb.getClassName(), true, sc.getClassLoader());
			ClassTestInterface obj = (ClassTestInterface) c.newInstance();

			logger.info(obj.getValue1("ini String"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClassBuilderTest app = new ClassBuilderTest();

		app.test1();
		app.test2();
	}

	class oke {
		public void test() {

		}

		class oke1 {
			public static final String test = "";

			public void test() {

			}
		}
	}
}