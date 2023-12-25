#include "pch-c.h"
#ifndef _MSC_VER
# include <alloca.h>
#else
# include <malloc.h>
#endif


#include "codegen/il2cpp-codegen-metadata.h"





// 0x00000001 System.Void UnityEngine.Font::InvokeTextureRebuilt_Internal(UnityEngine.Font)
extern void Font_InvokeTextureRebuilt_Internal_m874D1025267C908E5FCD437B41929E4DE248B01B (void);
// 0x00000002 System.Void UnityEngine.Font/FontTextureRebuildCallback::.ctor(System.Object,System.IntPtr)
extern void FontTextureRebuildCallback__ctor_m1AF27FC83F3136E493F47015F99CE7A4E6BCA0BC (void);
// 0x00000003 System.Void UnityEngine.Font/FontTextureRebuildCallback::Invoke()
extern void FontTextureRebuildCallback_Invoke_m8B52C3F4823ADBB80062209E6BA2B33202AE958D (void);
static Il2CppMethodPointer s_methodPointers[3] = 
{
	Font_InvokeTextureRebuilt_Internal_m874D1025267C908E5FCD437B41929E4DE248B01B,
	FontTextureRebuildCallback__ctor_m1AF27FC83F3136E493F47015F99CE7A4E6BCA0BC,
	FontTextureRebuildCallback_Invoke_m8B52C3F4823ADBB80062209E6BA2B33202AE958D,
};
static const int32_t s_InvokerIndices[3] = 
{
	2063,
	584,
	1074,
};
IL2CPP_EXTERN_C const Il2CppCodeGenModule g_UnityEngine_TextRenderingModule_CodeGenModule;
const Il2CppCodeGenModule g_UnityEngine_TextRenderingModule_CodeGenModule = 
{
	"UnityEngine.TextRenderingModule.dll",
	3,
	s_methodPointers,
	0,
	NULL,
	s_InvokerIndices,
	0,
	NULL,
	0,
	NULL,
	0,
	NULL,
	NULL,
	NULL, // module initializer,
	NULL,
	NULL,
	NULL,
};
