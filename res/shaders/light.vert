varying vec3 varyingNormal;
varying vec4 varyingVertex;

void main() {
    varyingNormal = gl_Normal;
    varyingVertex = gl_Vertex;
    gl_TexCoord[0] = gl_MultiTexCoord0;
    gl_FrontColor = gl_Color;
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}