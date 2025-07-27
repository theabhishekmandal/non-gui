package data_structures.disJoint_sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisJoinSetExample {
    public static void main(String[] args) {
        IDisJointSet disJointSetFastFind = new DisJoinSetFastFind();
        IDisJointSet disJointSetFastUnion = new DisJointSetFastUnion();
        IDisJointSet disJointSetFastUnionByRank = new DisJointSetFastUnionByRank();
        IDisJointSet disJointSetFastUnionByRankWithPathCompression = new DisJointSetFastUnionByRankWithPathCompression();

        List<IDisJointSet> list = new ArrayList<>(
                List.of(
                        disJointSetFastFind,
                        disJointSetFastUnion,
                        disJointSetFastUnionByRank,
                        disJointSetFastUnionByRankWithPathCompression
                )
        );


        List<String> names = List.of(
                "FastFind",
                "FastUnion",
                "UnionByRank",
                "UnionByRankWithPathCompression"
        );

        int size = 100_000_0; // adjust size as needed for benchmarking
        int numUnions = 90_000;
        int numFinds = 10_000;

        for (int i = 0; i < list.size(); i++) {
            IDisJointSet ds = list.get(i);
            String name = names.get(i);

            long startTime = System.nanoTime();

            // 1. Make set
            ds.makeSet(size);

            // 2. Perform union operations (0-1, 1-2, ..., 89999-90000)
            for (int j = 0; j < numUnions; j++) {
                ds.union(j, j + 1);
            }

            // 3. Perform some isConnected checks
            for (int j = 0; j < numFinds; j++) {
                ds.isConnected(j, size - j - 1);
            }

            long endTime = System.nanoTime();
            long durationMs = (endTime - startTime) / 1_000_000;

            System.out.printf("%-35s took %5d ms%n", name, durationMs);
        }
    }
}
