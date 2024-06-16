sealed class SealedA permits FinalB, NonSealedC {

}
// FinalB either (final, non-sealed, sealed)
final class FinalB extends SealedA {

}
non-sealed class NonSealedC extends SealedA{

}
class NormalD extends NonSealedC {

}
// Interface never be final (only sealed or non-sealed)
sealed interface InterfaceA permits InterfaceB {

}
non-sealed interface InterfaceB extends InterfaceA {

}
public class SealedClass {

}
