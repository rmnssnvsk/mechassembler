package util;

class Face {
    int[] vertices, textures, normals;
    String style;

    Face(int[] vertices, int[] textures, int[] normals, String style) {
        this.vertices = vertices;
        this.textures = textures;
        this.normals = normals;
        this.style = style;
    }
}
