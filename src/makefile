JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	raytracing/Color.java \
	raytracing/Vec3d.java \
	raytracing/InterfaceRay.java \
	raytracing/Plan.java \
	raytracing/Sphere.java \
	raytracing/Light.java \
	raytracing/Scene.java \
	raytracing/JavaTga.java 
	

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) raytracing/*.class
	$(RM) raytracing/*/*.class
