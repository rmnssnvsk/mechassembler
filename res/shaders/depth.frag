varying vec4 varyingVertex;

void main() {
    float vertexPosition = 1 + (gl_ModelViewMatrix * varyingVertex).z / 40;
    gl_FragColor = gl_Color * vertexPosition;
}