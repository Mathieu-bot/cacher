package fr.birdia.cacher.file.hash;

import fr.birdia.cacher.PojaGenerated;

@PojaGenerated
public record FileHash(FileHashAlgorithm algorithm, String value) {}
