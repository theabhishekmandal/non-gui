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

        int size = 100_000; // adjust size as needed for benchmarking
        int numUnions = 90_000;
        int numFinds = 10_000;

        for (int i = 0; i < list.size(); i++) {
            IDisJointSet ds = list.get(i);
            String name = names.get(i);

            // 1. Make Set
            long startMakeSet = System.nanoTime();
            ds.makeSet(size);
            long endMakeSet = System.nanoTime();

            // 2. Union phase
            long startUnion = System.nanoTime();
            for (int j = 0; j < numUnions; j++) {
                ds.union(j, j + 1);
            }
            long endUnion = System.nanoTime();

            // 3. First isConnected phase (may trigger path compression)
            long startFirstFind = System.nanoTime();
            for (int j = 0; j < numFinds; j++) {
                ds.isConnected(j, size - j - 1);
            }
            long endFirstFind = System.nanoTime();

            // 4. Second isConnected phase (reuses compression)
            long startSecondFind = System.nanoTime();
            for (int j = 0; j < numFinds; j++) {
                ds.isConnected(j, size - j - 1);
            }
            long endSecondFind = System.nanoTime();

            long msMakeSet      = (endMakeSet - startMakeSet) / 1_000_000;
            long msUnion        = (endUnion - startUnion) / 1_000_000;
            long msFirstFind    = (endFirstFind - startFirstFind) / 1_000_000;
            long msSecondFind   = (endSecondFind - startSecondFind) / 1_000_000;
            long total          = msMakeSet + msUnion + msFirstFind + msSecondFind;

            System.out.printf("%-35s: MakeSet=%4dms, Union=%4dms, Find1=%4dms, Find2=%4dms, Total=%4dms%n",
                    name, msMakeSet, msUnion, msFirstFind, msSecondFind, total);
        }

    }
}
