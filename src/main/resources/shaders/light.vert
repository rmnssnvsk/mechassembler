varying vec3 varyingNormal;
varying vec4 varyingVertex;
varying mat4 varyingViewMatrix;
uniform mat4 viewMatrix;

void main() {
    varyingNormal = gl_Normal;
    varyingVertex = gl_Vertex;
    varyingViewMatrix = viewMatrix;
    gl_TexCoord[0] = gl_MultiTexCoord0;
    gl_FrontColor = gl_Color;
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}