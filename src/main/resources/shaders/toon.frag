varying vec3 normal;
varying vec4 vertex;

void main() {
    vec3 vertexPosition = (gl_ModelViewMatrix * vertex).xyz;
    vec3 surfaceNormal = normalize((gl_NormalMatrix * normal).xyz);
    vec3 lightDirection = normalize(vertexPosition - gl_LightSource[0].position.xyz);
    float intensity = max(0, dot(surfaceNormal, lightDirection));

    if (intensity > 0.95)
        intensity = 1.0;
    else if (intensity > 0.5)
        intensity = 0.75;
    else if (intensity > 0.25)
        intensity = 0.5;
    else
        intensity = 0.25;

    gl_FragColor = intensity * gl_Color;
}