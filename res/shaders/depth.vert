varying vec4 varyingVertex;

void main() {
    varyingVertex = gl_Vertex;
    gl_FrontColor = gl_Color;
    gl_Position = ftransform();
}