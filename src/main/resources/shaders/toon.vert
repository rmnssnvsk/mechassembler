varying vec3 normal;
varying vec4 vertex;

void main() {
    normal = gl_Normal;
    vertex = gl_Vertex;
    gl_FrontColor = gl_Color;
    gl_Position = ftransform();
}