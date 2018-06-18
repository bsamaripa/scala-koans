package org.scalakoans

import org.scalatest.Sequential

class Koans extends Sequential {
  override val nestedSuites =
    List(
      new AboutAsserts,
      new AboutValAndVar,
      new AboutLiteralBooleans,
      new AboutLiteralNumbers,
      new AboutLiteralStrings,
      new AboutConstructors,
      new AboutTuples,
      new AboutLists,
      new AboutMaps,
      new AboutSets,
      new AboutSequencesAndArrays,
      new AboutMutableMaps,
      new AboutMutableSets,
      new AboutOptions,
      new AboutPatternMatching,
      new AboutCaseClasses,
      new AboutHigherOrderFunctions,
      new AboutPartiallyAppliedFunctions,
      new AboutPartialFunctions,
      new AboutForExpressions,
      new AboutEnumerations,
      new AboutEmptyValues,
      new AboutParentClasses,
      new AboutNamedAndDefaultArguments,
      new AboutInfixPrefixAndPostfixOperators,
      new AboutInfixTypes,
      new AboutAccessModifiers,
      new AboutTypeSignatures,
      new AboutTraits,
      new AboutImportsAndPackages,
      new AboutPreconditions,
      new AboutUniformAccessPrinciple,
      new AboutImplicits,
      new AboutInteroperability,
      new AboutManifests
    ).toIndexedSeq
}
