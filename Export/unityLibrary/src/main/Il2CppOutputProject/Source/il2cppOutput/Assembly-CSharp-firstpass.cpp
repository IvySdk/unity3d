#include "pch-cpp.hpp"

#ifndef _MSC_VER
# include <alloca.h>
#else
# include <malloc.h>
#endif


#include <limits>


struct VirtualActionInvoker0
{
	typedef void (*Action)(void*, const RuntimeMethod*);

	static inline void Invoke (Il2CppMethodSlot slot, RuntimeObject* obj)
	{
		const VirtualInvokeData& invokeData = il2cpp_codegen_get_virtual_invoke_data(slot, obj);
		((Action)invokeData.methodPtr)(obj, invokeData.method);
	}
};
template <typename T1>
struct VirtualActionInvoker1
{
	typedef void (*Action)(void*, T1, const RuntimeMethod*);

	static inline void Invoke (Il2CppMethodSlot slot, RuntimeObject* obj, T1 p1)
	{
		const VirtualInvokeData& invokeData = il2cpp_codegen_get_virtual_invoke_data(slot, obj);
		((Action)invokeData.methodPtr)(obj, p1, invokeData.method);
	}
};
template <typename T1, typename T2>
struct VirtualActionInvoker2
{
	typedef void (*Action)(void*, T1, T2, const RuntimeMethod*);

	static inline void Invoke (Il2CppMethodSlot slot, RuntimeObject* obj, T1 p1, T2 p2)
	{
		const VirtualInvokeData& invokeData = il2cpp_codegen_get_virtual_invoke_data(slot, obj);
		((Action)invokeData.methodPtr)(obj, p1, p2, invokeData.method);
	}
};
template <typename R>
struct VirtualFuncInvoker0
{
	typedef R (*Func)(void*, const RuntimeMethod*);

	static inline R Invoke (Il2CppMethodSlot slot, RuntimeObject* obj)
	{
		const VirtualInvokeData& invokeData = il2cpp_codegen_get_virtual_invoke_data(slot, obj);
		return ((Func)invokeData.methodPtr)(obj, invokeData.method);
	}
};
template <typename R, typename T1>
struct VirtualFuncInvoker1
{
	typedef R (*Func)(void*, T1, const RuntimeMethod*);

	static inline R Invoke (Il2CppMethodSlot slot, RuntimeObject* obj, T1 p1)
	{
		const VirtualInvokeData& invokeData = il2cpp_codegen_get_virtual_invoke_data(slot, obj);
		return ((Func)invokeData.methodPtr)(obj, p1, invokeData.method);
	}
};
struct InterfaceActionInvoker0
{
	typedef void (*Action)(void*, const RuntimeMethod*);

	static inline void Invoke (Il2CppMethodSlot slot, RuntimeClass* declaringInterface, RuntimeObject* obj)
	{
		const VirtualInvokeData& invokeData = il2cpp_codegen_get_interface_invoke_data(slot, obj, declaringInterface);
		((Action)invokeData.methodPtr)(obj, invokeData.method);
	}
};
template <typename R>
struct InterfaceFuncInvoker0
{
	typedef R (*Func)(void*, const RuntimeMethod*);

	static inline R Invoke (Il2CppMethodSlot slot, RuntimeClass* declaringInterface, RuntimeObject* obj)
	{
		const VirtualInvokeData& invokeData = il2cpp_codegen_get_interface_invoke_data(slot, obj, declaringInterface);
		return ((Func)invokeData.methodPtr)(obj, invokeData.method);
	}
};
template <typename R, typename T1>
struct InterfaceFuncInvoker1
{
	typedef R (*Func)(void*, T1, const RuntimeMethod*);

	static inline R Invoke (Il2CppMethodSlot slot, RuntimeClass* declaringInterface, RuntimeObject* obj, T1 p1)
	{
		const VirtualInvokeData& invokeData = il2cpp_codegen_get_interface_invoke_data(slot, obj, declaringInterface);
		return ((Func)invokeData.methodPtr)(obj, p1, invokeData.method);
	}
};

// System.Action`1<UnityEngine.AndroidJavaObject>
struct Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594;
// System.Action`1<System.Boolean>
struct Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C;
// System.Action`1<System.Object>
struct Action_1_t6F9EB113EB3F16226AEF811A2744F4111C116C87;
// System.Action`1<System.String>
struct Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A;
// System.Action`2<System.Int32Enum,System.Int32>
struct Action_2_t7B1DA294236CEB03E01D057BD5D4E8DCFCBF1811;
// System.Action`2<System.Int32Enum,System.Object>
struct Action_2_t829FF86EEA250A853DF3A95B2C4DAC034707E20F;
// System.Action`2<System.String,System.String>
struct Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D;
// System.Action`2<RiseSdk.RiseSdk/AFEventType,System.String>
struct Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4;
// System.Action`2<RiseSdk.RiseSdk/GMSEventType,System.String>
struct Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1;
// System.Action`2<RiseSdk.RiseSdk/PaymentResult,System.Int32>
struct Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F;
// System.Action`2<RiseSdk.RiseSdk/SnsEventType,System.Int32>
struct Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6;
// System.Action`3<System.Boolean,System.Int32,System.Object>
struct Action_3_t2740A37A77E4652A559511D11F6662C28174A796;
// System.Action`3<System.Boolean,System.Int32,System.String>
struct Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72;
// System.Action`3<System.Int32,System.Boolean,System.Object>
struct Action_3_tF1ADD96839CDCE68A6D0F397099A1E67C05F263B;
// System.Action`3<System.Int32,System.Boolean,System.String>
struct Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD;
// System.Action`4<System.Boolean,System.Boolean,System.Object,System.Object>
struct Action_4_t11E5CF24FE986CDC1E7FA7474E067F17AFB2B206;
// System.Action`4<System.Boolean,System.Boolean,System.String,System.String>
struct Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA;
// System.Action`4<System.Int32Enum,System.Int32,System.Object,System.Int32>
struct Action_4_t50E2C24F41BECB3E65BEB8FD155874C71203093C;
// System.Action`4<RiseSdk.RiseSdk/AdEventType,System.Int32,System.String,System.Int32>
struct Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5;
// System.Runtime.CompilerServices.ConditionalWeakTable`2<System.Object,System.Runtime.Serialization.SerializationInfo>
struct ConditionalWeakTable_2_t381B9D0186C0FCC3F83C0696C28C5001468A7858;
// System.Collections.Generic.Dictionary`2<System.Object,System.Int32>
struct Dictionary_2_t5C96F4B6841710A9013966F76224BAE01FB4B4D1;
// System.Collections.Generic.Dictionary`2<System.Object,System.Object>
struct Dictionary_2_t14FE4A752A83D53771C584E4C8D14E01F2AFD7BA;
// System.Collections.Generic.Dictionary`2<System.String,System.Int32>
struct Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588;
// System.Collections.Generic.Dictionary`2<System.String,System.Object>
struct Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710;
// System.Collections.Generic.Dictionary`2<System.String,System.String>
struct Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83;
// System.Func`1<System.Object>
struct Func_1_tD5C081AE11746B200C711DD48DBEB00E3A9276D4;
// System.Func`1<RiseSdk.RiseSdk>
struct Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39;
// System.Collections.Generic.IEqualityComparer`1<System.String>
struct IEqualityComparer_1_tAE94C8F24AD5B94D4EE85CA9FC59E3409D41CAF7;
// System.Collections.Generic.Dictionary`2/KeyCollection<System.String,System.Int32>
struct KeyCollection_tCC15D033281A6593E2488FAF5B205812A152AC03;
// System.Collections.Generic.Dictionary`2/KeyCollection<System.String,System.Object>
struct KeyCollection_tE66790F09E854C19C7F612BEAD203AE626E90A36;
// System.Collections.Generic.Dictionary`2/KeyCollection<System.String,System.String>
struct KeyCollection_t2EDD317F5771E575ACB63527B5AFB71291040342;
// System.Lazy`1<System.Object>
struct Lazy_1_tAD66CD7CC97BB996411F4FE0F49A1817031B906E;
// System.Lazy`1<RiseSdk.RiseSdk>
struct Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0;
// System.Collections.Generic.List`1<System.Object>
struct List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D;
// System.Collections.Generic.Dictionary`2/ValueCollection<System.String,System.Int32>
struct ValueCollection_tCE6BD704B9571C131E2D8C8CED569DDEC4AE042B;
// System.Collections.Generic.Dictionary`2/ValueCollection<System.String,System.Object>
struct ValueCollection_tC9D91E8A3198E40EA339059703AB10DFC9F5CC2E;
// System.Collections.Generic.Dictionary`2/ValueCollection<System.String,System.String>
struct ValueCollection_t238D0D2427C6B841A01F522A41540165A2C4AE76;
// System.Collections.Generic.Dictionary`2/Entry<System.String,System.Int32>[]
struct EntryU5BU5D_tEA0133B78B9FF7045128C508FA50247E525A94D6;
// System.Collections.Generic.Dictionary`2/Entry<System.String,System.Object>[]
struct EntryU5BU5D_t233BB24ED01E2D8D65B0651D54B8E3AD125CAF96;
// System.Collections.Generic.Dictionary`2/Entry<System.String,System.String>[]
struct EntryU5BU5D_t1AF33AD0B7330843448956EC4277517081658AE7;
// System.Byte[]
struct ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031;
// System.Char[]
struct CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB;
// System.Delegate[]
struct DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771;
// System.Int32[]
struct Int32U5BU5D_t19C97395396A72ECAF310612F0760F165060314C;
// System.IntPtr[]
struct IntPtrU5BU5D_tFD177F8C806A6921AD7150264CCC62FA00CAD832;
// System.Object[]
struct ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918;
// System.Diagnostics.StackTrace[]
struct StackTraceU5BU5D_t32FBCB20930EAF5BAE3F450FF75228E5450DA0DF;
// System.String[]
struct StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248;
// System.Type[]
struct TypeU5BU5D_t97234E1129B564EB38B8D85CAC2AD8B5B9522FFB;
// System.Collections.Hashtable/bucket[]
struct bucketU5BU5D_t59F1C7BC4EBFE874CA0B3F391EA65717E3C8D587;
// System.Action
struct Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07;
// UnityEngine.AndroidJavaClass
struct AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03;
// UnityEngine.AndroidJavaObject
struct AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0;
// UnityEngine.AndroidJavaRunnable
struct AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F;
// System.Collections.ArrayList
struct ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A;
// UnityEngine.Behaviour
struct Behaviour_t01970CFBBA658497AE30F311C447DB0440BAB7FA;
// System.Reflection.Binder
struct Binder_t91BFCE95A7057FADF4D8A1A342AFE52872246235;
// System.Delegate
struct Delegate_t;
// System.DelegateData
struct DelegateData_t9B286B493293CD2D23A5B2B5EF0E5B1324C2B77E;
// UnityEngine.GameObject
struct GameObject_t76FEDD663AB33C991A9C9A23129337651094216F;
// UnityEngine.GlobalJavaObjectRef
struct GlobalJavaObjectRef_t20D8E5AAFC2EB2518FCABBF40465855E797FF0D8;
// System.Collections.Hashtable
struct Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D;
// System.Collections.ICollection
struct ICollection_t37E7B9DC5B4EF41D190D607F92835BF1171C0E8E;
// System.Collections.IDictionary
struct IDictionary_t6D03155AF1FA9083817AA5B6AD7DEEACC26AB220;
// System.Collections.IEqualityComparer
struct IEqualityComparer_tEF8F1EC76B9C8E76695BE848D41E6B147928D1C1;
// System.Collections.IList
struct IList_t1C522956D79B7DC92B5B01053DF1AC058C8B598D;
// RiseSdk.IRiseSdk
struct IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E;
// System.LazyHelper
struct LazyHelper_t1784351780B2D1AC002869BB3C7A35AA64762602;
// System.Reflection.MemberFilter
struct MemberFilter_tF644F1AE82F611B677CE1964D5A3277DDA21D553;
// System.Reflection.MethodInfo
struct MethodInfo_t;
// MiniJSON
struct MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E;
// UnityEngine.MonoBehaviour
struct MonoBehaviour_t532A11E69716D348D8AA7F854AFCBFCB8AD17F71;
// UnityEngine.Object
struct Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C;
// RiseSdk.RiseSdk
struct RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F;
// RiseSdk.RiseSdkForAndroid
struct RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5;
// RiseSdk.RiseSdkForEditor
struct RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3;
// RiseSdk.RiseSdkForIOS
struct RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27;
// RiseSdk.RiseSdkListener
struct RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1;
// System.Runtime.Serialization.SafeSerializationManager
struct SafeSerializationManager_tCBB85B95DFD1634237140CD892E82D06ECB3F5E6;
// System.String
struct String_t;
// System.Text.StringBuilder
struct StringBuilder_t;
// System.IO.StringReader
struct StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8;
// System.IO.TextReader
struct TextReader_tB8D43017CB6BE1633E5A86D64E7757366507C1F7;
// System.Type
struct Type_t;
// System.Void
struct Void_t4861ACF8F4594C3437BB48B6E56783494B843915;
// RiseSdk.IRiseSdk/<>c__DisplayClass13_0
struct U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C;
// RiseSdk.IRiseSdk/<>c__DisplayClass14_0
struct U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B;
// RiseJson/Parser
struct Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0;
// RiseJson/Serializer
struct Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D;
// RiseSdk.RiseSdk/<>c
struct U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA;
// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0
struct U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D;
// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass12_0
struct U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76;
// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0
struct U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D;
// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass16_0
struct U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA;
// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0
struct U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D;

IL2CPP_EXTERN_C RuntimeClass* Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Byte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Char_t521A6F19B456D956AF452D926C32709DC03D6B17_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Double_tE150EF3D1D43DEE85D533810AB4C742307EEDE5F_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Exception_t_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* GameObject_t76FEDD663AB33C991A9C9A23129337651094216F_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* ICollection_t37E7B9DC5B4EF41D190D607F92835BF1171C0E8E_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* IDictionaryEnumerator_tE129D608FCDB7207E0F0ECE33473CC950A83AD16_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* IDictionary_t6D03155AF1FA9083817AA5B6AD7DEEACC26AB220_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* IEnumerable_t6331596D5DD37C462B1B8D49CF6B319B00AB7131_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* IEnumerator_t7B609C2FFA6EB5167D9C62A0C32A21DE2F666DAA_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* IList_t1C522956D79B7DC92B5B01053DF1AC058C8B598D_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Int16_tB8EF286A9C33492FA6E6D6E67320BE93E794A175_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Int64_t092CFB123BE63C28ACDAF65C68F21A526050DBA3_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* IntPtr_t_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* SByte_tFEFFEF5D2FEBF5207950AE6FAC150FC53B668DB5_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Single_t4530F2FF86FCB0DC29F35385CA1BD21BE294761C_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* StringBuilder_t_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* String_t_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* Type_t_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* UInt16_tF4C148C876015C212FD72652D0B6ED8CC247A455_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* UInt32_t1833D51FFA667B18A5AA4B8D34DE284F8495D29B_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C RuntimeClass* UInt64_t8F12534CC8FC4B5860F2A2CD1EE79D322E7A41AF_il2cpp_TypeInfo_var;
IL2CPP_EXTERN_C String_t* _stringLiteral0076CD8842170AB6C7A6A146C450B493C7365FCE;
IL2CPP_EXTERN_C String_t* _stringLiteral007AE806CCD78C75FA7CA260F0C6D94D9A985311;
IL2CPP_EXTERN_C String_t* _stringLiteral00B28FF06B788B9B67C6B259800F404F9F3761FD;
IL2CPP_EXTERN_C String_t* _stringLiteral053D8D6CEEBA9453C97D0EE5374DB863E6F77AD4;
IL2CPP_EXTERN_C String_t* _stringLiteral0770C8A4CE7977C0CF704EEA0FE098E008D76600;
IL2CPP_EXTERN_C String_t* _stringLiteral09B11B6CC411D8B9FFB75EAAE9A35B2AF248CE40;
IL2CPP_EXTERN_C String_t* _stringLiteral0A44B0E166277D89050FA0DBB49A6110AAC132EC;
IL2CPP_EXTERN_C String_t* _stringLiteral0C3C6829C3CCF8020C6AC45B87963ADC095CD44A;
IL2CPP_EXTERN_C String_t* _stringLiteral109B9483299C123690E4FB1FBE4442731D158EB5;
IL2CPP_EXTERN_C String_t* _stringLiteral130E17CA7453B93206D978925CBD030A85200E9C;
IL2CPP_EXTERN_C String_t* _stringLiteral14386C5B349364790D8B50DC2C99DA84A1515FD8;
IL2CPP_EXTERN_C String_t* _stringLiteral22E9F4C83DA6C833585AF2FF7338762737D7DF2F;
IL2CPP_EXTERN_C String_t* _stringLiteral24313380B89749FA23D81C8CFE7ECADF5F282DF3;
IL2CPP_EXTERN_C String_t* _stringLiteral36F1A2DE510A345520AE400CB46D98671631B2FD;
IL2CPP_EXTERN_C String_t* _stringLiteral3753DF735E896BD3C9F2E5D3740D83C85D747CF9;
IL2CPP_EXTERN_C String_t* _stringLiteral43DF4C12BD273F8942F5D6473A5E0E8A745FD24D;
IL2CPP_EXTERN_C String_t* _stringLiteral49E1E63F024DAB6D15BFDFE31F390544A9410DB2;
IL2CPP_EXTERN_C String_t* _stringLiteral4B2613D099EF787543CE5B1BEFD1E9EA92363726;
IL2CPP_EXTERN_C String_t* _stringLiteral4D613657609485AE586A3379BA0E3FC13C1E1078;
IL2CPP_EXTERN_C String_t* _stringLiteral4D8D9C94AC5DA5FCED2EC8A64E10E714A2515C30;
IL2CPP_EXTERN_C String_t* _stringLiteral4DD298A95417FB51C736D39EC400C2991FE38DDE;
IL2CPP_EXTERN_C String_t* _stringLiteral5962E944D7340CE47999BF097B4AFD70C1501FB9;
IL2CPP_EXTERN_C String_t* _stringLiteral5BEFD8CC60A79699B5BB00E37BAC5B62D371E174;
IL2CPP_EXTERN_C String_t* _stringLiteral63A7BC5625BD73B4EFFB4EB777EA75B79C73E722;
IL2CPP_EXTERN_C String_t* _stringLiteral750483B1B79D7876103D12AD91A365DD2A867A80;
IL2CPP_EXTERN_C String_t* _stringLiteral758733BDBED83CBFF4F635AC26CA92AAE477F75D;
IL2CPP_EXTERN_C String_t* _stringLiteral75BA519FAA66BEB6FD5B3EEE9D8CA9B41F1D2549;
IL2CPP_EXTERN_C String_t* _stringLiteral77D38C0623F92B292B925F6E72CF5CF99A20D4EB;
IL2CPP_EXTERN_C String_t* _stringLiteral785F17F45C331C415D0A7458E6AAC36966399C51;
IL2CPP_EXTERN_C String_t* _stringLiteral79CD4A07010B3D7A9E573D3FCBE8E6B7EEFFABD3;
IL2CPP_EXTERN_C String_t* _stringLiteral7A61131F8C86302B298AECB0C8394280E5DAE93E;
IL2CPP_EXTERN_C String_t* _stringLiteral7D5D6BBF8281151C9F5F57DE5D5BABB7140A651D;
IL2CPP_EXTERN_C String_t* _stringLiteral7F3238CD8C342B06FB9AB185C610175C84625462;
IL2CPP_EXTERN_C String_t* _stringLiteral848E5ED630B3142F565DD995C6E8D30187ED33CD;
IL2CPP_EXTERN_C String_t* _stringLiteral86BBAACC00198DBB3046818AD3FC2AA10AE48DE1;
IL2CPP_EXTERN_C String_t* _stringLiteral875A41C8F1EEC1DEF871C0E7703977E3BC52359F;
IL2CPP_EXTERN_C String_t* _stringLiteral876C4B39B6E4D0187090400768899C71D99DE90D;
IL2CPP_EXTERN_C String_t* _stringLiteral8F9B51CCF3AF3FFAF9266651B8748CEC7B6E3F23;
IL2CPP_EXTERN_C String_t* _stringLiteral91846EFA3E6ED5AF1BC2957F86BFD3C16A8ACEA2;
IL2CPP_EXTERN_C String_t* _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
IL2CPP_EXTERN_C String_t* _stringLiteralA539038315AABE91ABF55F3F1AEE619EEBFC183A;
IL2CPP_EXTERN_C String_t* _stringLiteralA77B332C46D848F790D53EDB5E0641C50ED83741;
IL2CPP_EXTERN_C String_t* _stringLiteralA7C3FCA8C63E127B542B38A5CA5E3FEEDDD1B122;
IL2CPP_EXTERN_C String_t* _stringLiteralB78F235D4291950A7D101307609C259F3E1F033F;
IL2CPP_EXTERN_C String_t* _stringLiteralB7C45DD316C68ABF3429C20058C2981C652192F2;
IL2CPP_EXTERN_C String_t* _stringLiteralBE120FB49F923996294FC08FABA8FD2AE35AB761;
IL2CPP_EXTERN_C String_t* _stringLiteralC386789464914AFAB69C70A3E07C88FB68EDB4DB;
IL2CPP_EXTERN_C String_t* _stringLiteralC62C64F00567C5368CAE37F4E64E1E82FF785677;
IL2CPP_EXTERN_C String_t* _stringLiteralD766ED5EEC6B619ADF99610DC9A7EFCBA8F696BA;
IL2CPP_EXTERN_C String_t* _stringLiteralD9691C4FD8A1F6B09DB1147CA32B442772FB46A1;
IL2CPP_EXTERN_C String_t* _stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709;
IL2CPP_EXTERN_C String_t* _stringLiteralDA666908BB15F4E1D2649752EC5DCBD0D5C64699;
IL2CPP_EXTERN_C String_t* _stringLiteralDA88A2D964F57B37C63183F5236085B715F076B8;
IL2CPP_EXTERN_C String_t* _stringLiteralDB5B55A9B215F744DB82517864984D073F2E8F8C;
IL2CPP_EXTERN_C String_t* _stringLiteralDDB5C5392F2AC4F8FA233E2838582017917B2D60;
IL2CPP_EXTERN_C String_t* _stringLiteralDE28F98354F48E7C0878BBA93033C6BDC68B27E2;
IL2CPP_EXTERN_C String_t* _stringLiteralE166C9564FBDE461738077E3B1B506525EB6ACCC;
IL2CPP_EXTERN_C String_t* _stringLiteralE68E2F12E92A958E7B766A79531F38C5AE56AAFC;
IL2CPP_EXTERN_C String_t* _stringLiteralEBC658B067B5C785A3F0BB67D73755F6FEE7F70C;
IL2CPP_EXTERN_C String_t* _stringLiteralEEB922CBCE2FEF1F2C3AE253CCEBC5CD15A2CE58;
IL2CPP_EXTERN_C String_t* _stringLiteralF0DCFEDBF422508D62EFFA422BCCE4309448C1E9;
IL2CPP_EXTERN_C String_t* _stringLiteralF17AE3F6EE79ABAD493C10DCE41D5CDBDA4D91E9;
IL2CPP_EXTERN_C String_t* _stringLiteralF18840F490E42D3CE48CDCBF47229C1C240F8ABE;
IL2CPP_EXTERN_C String_t* _stringLiteralF1B4DDA1D30D45F9E87828D144D13ECAC3525591;
IL2CPP_EXTERN_C String_t* _stringLiteralF468E0BCDE9855E7830073A32AF7323CC7E46952;
IL2CPP_EXTERN_C String_t* _stringLiteralF944DCD635F9801F7AC90A407FBC479964DEC024;
IL2CPP_EXTERN_C String_t* _stringLiteralFB4AE4F77150C3A8E8E4F8B23E734E0C7277B7D9;
IL2CPP_EXTERN_C String_t* _stringLiteralFC9082054BCCBFE90F98613132B91AB86CA0B6EA;
IL2CPP_EXTERN_C const RuntimeMethod* AndroidJavaObject_CallStatic_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mE956BC9A30BEC746DE593C53C1B8DB6A685185A6_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* AndroidJavaObject_Call_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_m020246E0988293B6126B690BD6CE4D894276AA3D_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Dictionary_2_Add_m2FE98C9C3763E31D7CB55207ED3A46B33BF64883_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Dictionary_2_GetEnumerator_m46EC45F42CA2279D83568CD3F216AAABA8E749F6_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Dictionary_2_TryGetValue_m835BB1E6EA8A8BF1242B51E28FD65B43FEF68E2A_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Dictionary_2__ctor_mBB2DBA9ECB2AD6046CB4CFB717FDD7E474A439AB_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Dictionary_2__ctor_mC4F3DF292BAD88F4BF193C49CD689FAEBC4570A9_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Dictionary_2_set_Item_m7CCA97075B48AFB2B97E5A072B94BC7679374341_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Enumerator_Dispose_m068DDFF5CAFBB15C8A0602DEADA7F10C5BB7ADCD_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Enumerator_MoveNext_mA93491D9B55547D066053F3BC0A69C635F877438_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Enumerator_get_Current_m49070E88C2E34AB46E6292A3FB1C227576B8506E_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* GameObject_AddComponent_TisRiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_mBBD0626F5E8E30C9E5DE7AF8642C92E276F70A26_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* KeyValuePair_2_get_Key_m654BCCAE2F20CB11D8E8C2D2C886A0C8A13EB1C4_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* KeyValuePair_2_get_Value_m7345512A32CB4DCAA0643050B18DC8DCD71B927A_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Lazy_1__ctor_mDA25196CB38DB23B1A01521353908954A84CDEDA_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* Lazy_1_get_Value_m1CFEA24735C8452E6D5A843230294721DBAB2033_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* List_1_Add_mEBCF994CC3814631017F46A387B1A192ED6C85C7_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* List_1__ctor_m7F078BB342729BDF11327FD89D7872265328F690_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* RiseSdkForAndroid_U3COnInitU3Eb__4_0_m1082EE1D20F7ACFE9BBF7AF99290E1E4CA36E8B9_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* RiseSdkForAndroid_U3COnPauseU3Eb__5_0_m34FB82A87E0DD7F49A453FBF31E608F8A88FDDF0_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* RiseSdkForAndroid_U3COnResumeU3Eb__6_0_m0135D676E784975A363AB0DD9D97D9E0AA942A11_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* RiseSdkForAndroid_U3COnStartU3Eb__7_0_m89B8844DF78386CA08E46BEF64616448A7DB922D_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* RiseSdkForAndroid_U3COnStopU3Eb__8_0_m06252C6CA773D404BD4F852CF7D0C77448A806C0_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* U3CU3Ec_U3C_cctorU3Eb__31_0_mD8C63722A480DC75713E42C5649AEF00E1927A25_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* U3CU3Ec__DisplayClass10_0_U3CrecordExceptionU3Eb__0_m04E2BE8643342EB115E4B797B549DCB094F42C9C_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* U3CU3Ec__DisplayClass12_0_U3CShowRewardAdU3Eb__0_m9A7E731D46232CFFB16D0374F9E423976B407617_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* U3CU3Ec__DisplayClass13_0_U3CCallSafeOnMainThreadU3Eb__0_mEDBBC07F399DCFF0ABF83639ACF8D9D17B04D5FD_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* U3CU3Ec__DisplayClass13_0_U3CShowRewardAdU3Eb__0_m3DB00AEFDF6C1BF09F3E5BA65981A7E529730D98_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* U3CU3Ec__DisplayClass14_0_U3CCallSafeOnMainThread2U3Eb__0_m904DAFB6EB35FCAE052734C5BEB981562E3789CB_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* U3CU3Ec__DisplayClass16_0_U3CToastMsgU3Eb__0_mBC6044148B319EB50771BD88920AC3718077E536_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeMethod* U3CU3Ec__DisplayClass9_0_U3CFireBaseTrackEventU3Eb__0_m2025C69CA2D4A54ED89390426A9209D30187CC82_RuntimeMethod_var;
IL2CPP_EXTERN_C const RuntimeType* RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_0_0_0_var;
struct Delegate_t_marshaled_com;
struct Delegate_t_marshaled_pinvoke;
struct Exception_t_marshaled_com;
struct Exception_t_marshaled_pinvoke;

struct CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB;
struct DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771;
struct ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918;
struct StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248;

IL2CPP_EXTERN_C_BEGIN
IL2CPP_EXTERN_C_END

#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif

// <Module>
struct U3CModuleU3E_tF062866229C4952B8051AD32AB6E9D931142CC95 
{
};

// System.Collections.Generic.Dictionary`2<System.String,System.Int32>
struct Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588  : public RuntimeObject
{
	// System.Int32[] System.Collections.Generic.Dictionary`2::_buckets
	Int32U5BU5D_t19C97395396A72ECAF310612F0760F165060314C* ____buckets_0;
	// System.Collections.Generic.Dictionary`2/Entry<TKey,TValue>[] System.Collections.Generic.Dictionary`2::_entries
	EntryU5BU5D_tEA0133B78B9FF7045128C508FA50247E525A94D6* ____entries_1;
	// System.Int32 System.Collections.Generic.Dictionary`2::_count
	int32_t ____count_2;
	// System.Int32 System.Collections.Generic.Dictionary`2::_freeList
	int32_t ____freeList_3;
	// System.Int32 System.Collections.Generic.Dictionary`2::_freeCount
	int32_t ____freeCount_4;
	// System.Int32 System.Collections.Generic.Dictionary`2::_version
	int32_t ____version_5;
	// System.Collections.Generic.IEqualityComparer`1<TKey> System.Collections.Generic.Dictionary`2::_comparer
	RuntimeObject* ____comparer_6;
	// System.Collections.Generic.Dictionary`2/KeyCollection<TKey,TValue> System.Collections.Generic.Dictionary`2::_keys
	KeyCollection_tCC15D033281A6593E2488FAF5B205812A152AC03* ____keys_7;
	// System.Collections.Generic.Dictionary`2/ValueCollection<TKey,TValue> System.Collections.Generic.Dictionary`2::_values
	ValueCollection_tCE6BD704B9571C131E2D8C8CED569DDEC4AE042B* ____values_8;
	// System.Object System.Collections.Generic.Dictionary`2::_syncRoot
	RuntimeObject* ____syncRoot_9;
};

// System.Collections.Generic.Dictionary`2<System.String,System.Object>
struct Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710  : public RuntimeObject
{
	// System.Int32[] System.Collections.Generic.Dictionary`2::_buckets
	Int32U5BU5D_t19C97395396A72ECAF310612F0760F165060314C* ____buckets_0;
	// System.Collections.Generic.Dictionary`2/Entry<TKey,TValue>[] System.Collections.Generic.Dictionary`2::_entries
	EntryU5BU5D_t233BB24ED01E2D8D65B0651D54B8E3AD125CAF96* ____entries_1;
	// System.Int32 System.Collections.Generic.Dictionary`2::_count
	int32_t ____count_2;
	// System.Int32 System.Collections.Generic.Dictionary`2::_freeList
	int32_t ____freeList_3;
	// System.Int32 System.Collections.Generic.Dictionary`2::_freeCount
	int32_t ____freeCount_4;
	// System.Int32 System.Collections.Generic.Dictionary`2::_version
	int32_t ____version_5;
	// System.Collections.Generic.IEqualityComparer`1<TKey> System.Collections.Generic.Dictionary`2::_comparer
	RuntimeObject* ____comparer_6;
	// System.Collections.Generic.Dictionary`2/KeyCollection<TKey,TValue> System.Collections.Generic.Dictionary`2::_keys
	KeyCollection_tE66790F09E854C19C7F612BEAD203AE626E90A36* ____keys_7;
	// System.Collections.Generic.Dictionary`2/ValueCollection<TKey,TValue> System.Collections.Generic.Dictionary`2::_values
	ValueCollection_tC9D91E8A3198E40EA339059703AB10DFC9F5CC2E* ____values_8;
	// System.Object System.Collections.Generic.Dictionary`2::_syncRoot
	RuntimeObject* ____syncRoot_9;
};

// System.Collections.Generic.Dictionary`2<System.String,System.String>
struct Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83  : public RuntimeObject
{
	// System.Int32[] System.Collections.Generic.Dictionary`2::_buckets
	Int32U5BU5D_t19C97395396A72ECAF310612F0760F165060314C* ____buckets_0;
	// System.Collections.Generic.Dictionary`2/Entry<TKey,TValue>[] System.Collections.Generic.Dictionary`2::_entries
	EntryU5BU5D_t1AF33AD0B7330843448956EC4277517081658AE7* ____entries_1;
	// System.Int32 System.Collections.Generic.Dictionary`2::_count
	int32_t ____count_2;
	// System.Int32 System.Collections.Generic.Dictionary`2::_freeList
	int32_t ____freeList_3;
	// System.Int32 System.Collections.Generic.Dictionary`2::_freeCount
	int32_t ____freeCount_4;
	// System.Int32 System.Collections.Generic.Dictionary`2::_version
	int32_t ____version_5;
	// System.Collections.Generic.IEqualityComparer`1<TKey> System.Collections.Generic.Dictionary`2::_comparer
	RuntimeObject* ____comparer_6;
	// System.Collections.Generic.Dictionary`2/KeyCollection<TKey,TValue> System.Collections.Generic.Dictionary`2::_keys
	KeyCollection_t2EDD317F5771E575ACB63527B5AFB71291040342* ____keys_7;
	// System.Collections.Generic.Dictionary`2/ValueCollection<TKey,TValue> System.Collections.Generic.Dictionary`2::_values
	ValueCollection_t238D0D2427C6B841A01F522A41540165A2C4AE76* ____values_8;
	// System.Object System.Collections.Generic.Dictionary`2::_syncRoot
	RuntimeObject* ____syncRoot_9;
};

// System.EmptyArray`1<System.Object>
struct EmptyArray_1_tDF0DD7256B115243AA6BD5558417387A734240EE  : public RuntimeObject
{
};

// System.Lazy`1<RiseSdk.RiseSdk>
struct Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0  : public RuntimeObject
{
	// System.LazyHelper modreq(System.Runtime.CompilerServices.IsVolatile) System.Lazy`1::_state
	LazyHelper_t1784351780B2D1AC002869BB3C7A35AA64762602* ____state_0;
	// System.Func`1<T> System.Lazy`1::_factory
	Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39* ____factory_1;
	// T System.Lazy`1::_value
	RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* ____value_2;
};

// System.Collections.Generic.List`1<System.Object>
struct List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D  : public RuntimeObject
{
	// T[] System.Collections.Generic.List`1::_items
	ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ____items_1;
	// System.Int32 System.Collections.Generic.List`1::_size
	int32_t ____size_2;
	// System.Int32 System.Collections.Generic.List`1::_version
	int32_t ____version_3;
	// System.Object System.Collections.Generic.List`1::_syncRoot
	RuntimeObject* ____syncRoot_4;
};

// UnityEngine.AndroidJavaObject
struct AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0  : public RuntimeObject
{
	// UnityEngine.GlobalJavaObjectRef UnityEngine.AndroidJavaObject::m_jobject
	GlobalJavaObjectRef_t20D8E5AAFC2EB2518FCABBF40465855E797FF0D8* ___m_jobject_1;
	// UnityEngine.GlobalJavaObjectRef UnityEngine.AndroidJavaObject::m_jclass
	GlobalJavaObjectRef_t20D8E5AAFC2EB2518FCABBF40465855E797FF0D8* ___m_jclass_2;
};

// System.Collections.ArrayList
struct ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A  : public RuntimeObject
{
	// System.Object[] System.Collections.ArrayList::_items
	ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ____items_0;
	// System.Int32 System.Collections.ArrayList::_size
	int32_t ____size_1;
	// System.Int32 System.Collections.ArrayList::_version
	int32_t ____version_2;
	// System.Object System.Collections.ArrayList::_syncRoot
	RuntimeObject* ____syncRoot_3;
};

// System.Collections.Hashtable
struct Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D  : public RuntimeObject
{
	// System.Collections.Hashtable/bucket[] System.Collections.Hashtable::_buckets
	bucketU5BU5D_t59F1C7BC4EBFE874CA0B3F391EA65717E3C8D587* ____buckets_10;
	// System.Int32 System.Collections.Hashtable::_count
	int32_t ____count_11;
	// System.Int32 System.Collections.Hashtable::_occupancy
	int32_t ____occupancy_12;
	// System.Int32 System.Collections.Hashtable::_loadsize
	int32_t ____loadsize_13;
	// System.Single System.Collections.Hashtable::_loadFactor
	float ____loadFactor_14;
	// System.Int32 modreq(System.Runtime.CompilerServices.IsVolatile) System.Collections.Hashtable::_version
	int32_t ____version_15;
	// System.Boolean modreq(System.Runtime.CompilerServices.IsVolatile) System.Collections.Hashtable::_isWriterInProgress
	bool ____isWriterInProgress_16;
	// System.Collections.ICollection System.Collections.Hashtable::_keys
	RuntimeObject* ____keys_17;
	// System.Collections.ICollection System.Collections.Hashtable::_values
	RuntimeObject* ____values_18;
	// System.Collections.IEqualityComparer System.Collections.Hashtable::_keycomparer
	RuntimeObject* ____keycomparer_19;
	// System.Object System.Collections.Hashtable::_syncRoot
	RuntimeObject* ____syncRoot_20;
};

// RiseSdk.IRiseSdk
struct IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E  : public RuntimeObject
{
};

// System.MarshalByRefObject
struct MarshalByRefObject_t8C2F4C5854177FD60439EB1FCCFC1B3CFAFE8DCE  : public RuntimeObject
{
	// System.Object System.MarshalByRefObject::_identity
	RuntimeObject* ____identity_0;
};
// Native definition for P/Invoke marshalling of System.MarshalByRefObject
struct MarshalByRefObject_t8C2F4C5854177FD60439EB1FCCFC1B3CFAFE8DCE_marshaled_pinvoke
{
	Il2CppIUnknown* ____identity_0;
};
// Native definition for COM marshalling of System.MarshalByRefObject
struct MarshalByRefObject_t8C2F4C5854177FD60439EB1FCCFC1B3CFAFE8DCE_marshaled_com
{
	Il2CppIUnknown* ____identity_0;
};

// System.Reflection.MemberInfo
struct MemberInfo_t  : public RuntimeObject
{
};

// MiniJSON
struct MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E  : public RuntimeObject
{
};

// MiniJsonExtensions
struct MiniJsonExtensions_t28F8DC8B7B3E5863B564FAC8283637E80FBDDADB  : public RuntimeObject
{
};

// RiseJson
struct RiseJson_t4035E011500364ECC995F1FA3EBEF522FFE83D0A  : public RuntimeObject
{
};

// System.String
struct String_t  : public RuntimeObject
{
	// System.Int32 System.String::_stringLength
	int32_t ____stringLength_4;
	// System.Char System.String::_firstChar
	Il2CppChar ____firstChar_5;
};

// System.Text.StringBuilder
struct StringBuilder_t  : public RuntimeObject
{
	// System.Char[] System.Text.StringBuilder::m_ChunkChars
	CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___m_ChunkChars_0;
	// System.Text.StringBuilder System.Text.StringBuilder::m_ChunkPrevious
	StringBuilder_t* ___m_ChunkPrevious_1;
	// System.Int32 System.Text.StringBuilder::m_ChunkLength
	int32_t ___m_ChunkLength_2;
	// System.Int32 System.Text.StringBuilder::m_ChunkOffset
	int32_t ___m_ChunkOffset_3;
	// System.Int32 System.Text.StringBuilder::m_MaxCapacity
	int32_t ___m_MaxCapacity_4;
};

// System.ValueType
struct ValueType_t6D9B272BD21782F0A9A14F2E41F85A50E97A986F  : public RuntimeObject
{
};
// Native definition for P/Invoke marshalling of System.ValueType
struct ValueType_t6D9B272BD21782F0A9A14F2E41F85A50E97A986F_marshaled_pinvoke
{
};
// Native definition for COM marshalling of System.ValueType
struct ValueType_t6D9B272BD21782F0A9A14F2E41F85A50E97A986F_marshaled_com
{
};

// RiseSdk.IRiseSdk/<>c__DisplayClass13_0
struct U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C  : public RuntimeObject
{
	// System.Action RiseSdk.IRiseSdk/<>c__DisplayClass13_0::action
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* ___action_0;
};

// RiseSdk.IRiseSdk/<>c__DisplayClass14_0
struct U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B  : public RuntimeObject
{
	// System.Action`1<UnityEngine.AndroidJavaObject> RiseSdk.IRiseSdk/<>c__DisplayClass14_0::action
	Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* ___action_0;
	// UnityEngine.AndroidJavaObject RiseSdk.IRiseSdk/<>c__DisplayClass14_0::activity
	AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* ___activity_1;
};

// RiseJson/Parser
struct Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0  : public RuntimeObject
{
	// System.IO.StringReader RiseJson/Parser::json
	StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* ___json_1;
};

// RiseJson/Serializer
struct Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D  : public RuntimeObject
{
	// System.Text.StringBuilder RiseJson/Serializer::builder
	StringBuilder_t* ___builder_0;
};

// RiseSdk.RiseSdk/<>c
struct U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA  : public RuntimeObject
{
};

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0
struct U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D  : public RuntimeObject
{
	// RiseSdk.RiseSdkForAndroid RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0::<>4__this
	RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* ___U3CU3E4__this_0;
	// System.String RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0::title
	String_t* ___title_1;
	// System.String RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0::err
	String_t* ___err_2;
};

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass12_0
struct U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76  : public RuntimeObject
{
	// RiseSdk.RiseSdkForAndroid RiseSdk.RiseSdkForAndroid/<>c__DisplayClass12_0::<>4__this
	RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* ___U3CU3E4__this_0;
	// System.Int32 RiseSdk.RiseSdkForAndroid/<>c__DisplayClass12_0::rewardId
	int32_t ___rewardId_1;
};

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0
struct U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D  : public RuntimeObject
{
	// RiseSdk.RiseSdkForAndroid RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0::<>4__this
	RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* ___U3CU3E4__this_0;
	// System.String RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0::tag
	String_t* ___tag_1;
	// System.Int32 RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0::rewardId
	int32_t ___rewardId_2;
};

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass16_0
struct U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA  : public RuntimeObject
{
	// RiseSdk.RiseSdkForAndroid RiseSdk.RiseSdkForAndroid/<>c__DisplayClass16_0::<>4__this
	RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* ___U3CU3E4__this_0;
	// System.String RiseSdk.RiseSdkForAndroid/<>c__DisplayClass16_0::str
	String_t* ___str_1;
};

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0
struct U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D  : public RuntimeObject
{
	// RiseSdk.RiseSdkForAndroid RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0::<>4__this
	RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* ___U3CU3E4__this_0;
	// System.String RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0::category
	String_t* ___category_1;
	// System.String RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0::keyValueData
	String_t* ___keyValueData_2;
};

// System.Collections.Generic.KeyValuePair`2<System.Object,System.Object>
struct KeyValuePair_2_tFC32D2507216293851350D29B64D79F950B55230 
{
	// TKey System.Collections.Generic.KeyValuePair`2::key
	RuntimeObject* ___key_0;
	// TValue System.Collections.Generic.KeyValuePair`2::value
	RuntimeObject* ___value_1;
};

// System.Collections.Generic.KeyValuePair`2<System.String,System.String>
struct KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A 
{
	// TKey System.Collections.Generic.KeyValuePair`2::key
	String_t* ___key_0;
	// TValue System.Collections.Generic.KeyValuePair`2::value
	String_t* ___value_1;
};

// UnityEngine.AndroidJavaClass
struct AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03  : public AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0
{
};

// System.Boolean
struct Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22 
{
	// System.Boolean System.Boolean::m_value
	bool ___m_value_0;
};

// System.Byte
struct Byte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3 
{
	// System.Byte System.Byte::m_value
	uint8_t ___m_value_0;
};

// System.Char
struct Char_t521A6F19B456D956AF452D926C32709DC03D6B17 
{
	// System.Char System.Char::m_value
	Il2CppChar ___m_value_0;
};

// System.Decimal
struct Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F 
{
	union
	{
		#pragma pack(push, tp, 1)
		struct
		{
			// System.Int32 System.Decimal::flags
			int32_t ___flags_8;
		};
		#pragma pack(pop, tp)
		struct
		{
			int32_t ___flags_8_forAlignmentOnly;
		};
		#pragma pack(push, tp, 1)
		struct
		{
			char ___hi_9_OffsetPadding[4];
			// System.Int32 System.Decimal::hi
			int32_t ___hi_9;
		};
		#pragma pack(pop, tp)
		struct
		{
			char ___hi_9_OffsetPadding_forAlignmentOnly[4];
			int32_t ___hi_9_forAlignmentOnly;
		};
		#pragma pack(push, tp, 1)
		struct
		{
			char ___lo_10_OffsetPadding[8];
			// System.Int32 System.Decimal::lo
			int32_t ___lo_10;
		};
		#pragma pack(pop, tp)
		struct
		{
			char ___lo_10_OffsetPadding_forAlignmentOnly[8];
			int32_t ___lo_10_forAlignmentOnly;
		};
		#pragma pack(push, tp, 1)
		struct
		{
			char ___mid_11_OffsetPadding[12];
			// System.Int32 System.Decimal::mid
			int32_t ___mid_11;
		};
		#pragma pack(pop, tp)
		struct
		{
			char ___mid_11_OffsetPadding_forAlignmentOnly[12];
			int32_t ___mid_11_forAlignmentOnly;
		};
		#pragma pack(push, tp, 1)
		struct
		{
			char ___ulomidLE_12_OffsetPadding[8];
			// System.UInt64 System.Decimal::ulomidLE
			uint64_t ___ulomidLE_12;
		};
		#pragma pack(pop, tp)
		struct
		{
			char ___ulomidLE_12_OffsetPadding_forAlignmentOnly[8];
			uint64_t ___ulomidLE_12_forAlignmentOnly;
		};
	};
};

// System.Double
struct Double_tE150EF3D1D43DEE85D533810AB4C742307EEDE5F 
{
	// System.Double System.Double::m_value
	double ___m_value_0;
};

// System.Int16
struct Int16_tB8EF286A9C33492FA6E6D6E67320BE93E794A175 
{
	// System.Int16 System.Int16::m_value
	int16_t ___m_value_0;
};

// System.Int32
struct Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C 
{
	// System.Int32 System.Int32::m_value
	int32_t ___m_value_0;
};

// System.Int64
struct Int64_t092CFB123BE63C28ACDAF65C68F21A526050DBA3 
{
	// System.Int64 System.Int64::m_value
	int64_t ___m_value_0;
};

// System.IntPtr
struct IntPtr_t 
{
	// System.Void* System.IntPtr::m_value
	void* ___m_value_0;
};

// RiseSdk.RiseSdk
struct RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F  : public IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E
{
	// RiseSdk.IRiseSdk RiseSdk.RiseSdk::_riseSdk
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* ____riseSdk_7;
};

// RiseSdk.RiseSdkForAndroid
struct RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5  : public IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E
{
	// UnityEngine.AndroidJavaClass RiseSdk.RiseSdkForAndroid::_class
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* ____class_0;
};

// RiseSdk.RiseSdkForEditor
struct RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3  : public IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E
{
	// System.Boolean RiseSdk.RiseSdkForEditor::_isAdsEnabled
	bool ____isAdsEnabled_0;
};

// RiseSdk.RiseSdkForIOS
struct RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27  : public IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E
{
};

// System.SByte
struct SByte_tFEFFEF5D2FEBF5207950AE6FAC150FC53B668DB5 
{
	// System.SByte System.SByte::m_value
	int8_t ___m_value_0;
};

// System.Single
struct Single_t4530F2FF86FCB0DC29F35385CA1BD21BE294761C 
{
	// System.Single System.Single::m_value
	float ___m_value_0;
};

// System.IO.TextReader
struct TextReader_tB8D43017CB6BE1633E5A86D64E7757366507C1F7  : public MarshalByRefObject_t8C2F4C5854177FD60439EB1FCCFC1B3CFAFE8DCE
{
};

// System.UInt16
struct UInt16_tF4C148C876015C212FD72652D0B6ED8CC247A455 
{
	// System.UInt16 System.UInt16::m_value
	uint16_t ___m_value_0;
};

// System.UInt32
struct UInt32_t1833D51FFA667B18A5AA4B8D34DE284F8495D29B 
{
	// System.UInt32 System.UInt32::m_value
	uint32_t ___m_value_0;
};

// System.UInt64
struct UInt64_t8F12534CC8FC4B5860F2A2CD1EE79D322E7A41AF 
{
	// System.UInt64 System.UInt64::m_value
	uint64_t ___m_value_0;
};

// System.Void
struct Void_t4861ACF8F4594C3437BB48B6E56783494B843915 
{
	union
	{
		struct
		{
		};
		uint8_t Void_t4861ACF8F4594C3437BB48B6E56783494B843915__padding[1];
	};
};

// System.Collections.Generic.Dictionary`2/Enumerator<System.Object,System.Object>
struct Enumerator_tEA93FE2B778D098F590CA168BEFC4CD85D73A6B9 
{
	// System.Collections.Generic.Dictionary`2<TKey,TValue> System.Collections.Generic.Dictionary`2/Enumerator::_dictionary
	Dictionary_2_t14FE4A752A83D53771C584E4C8D14E01F2AFD7BA* ____dictionary_0;
	// System.Int32 System.Collections.Generic.Dictionary`2/Enumerator::_version
	int32_t ____version_1;
	// System.Int32 System.Collections.Generic.Dictionary`2/Enumerator::_index
	int32_t ____index_2;
	// System.Collections.Generic.KeyValuePair`2<TKey,TValue> System.Collections.Generic.Dictionary`2/Enumerator::_current
	KeyValuePair_2_tFC32D2507216293851350D29B64D79F950B55230 ____current_3;
	// System.Int32 System.Collections.Generic.Dictionary`2/Enumerator::_getEnumeratorRetType
	int32_t ____getEnumeratorRetType_4;
};

// System.Collections.Generic.Dictionary`2/Enumerator<System.String,System.String>
struct Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562 
{
	// System.Collections.Generic.Dictionary`2<TKey,TValue> System.Collections.Generic.Dictionary`2/Enumerator::_dictionary
	Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83* ____dictionary_0;
	// System.Int32 System.Collections.Generic.Dictionary`2/Enumerator::_version
	int32_t ____version_1;
	// System.Int32 System.Collections.Generic.Dictionary`2/Enumerator::_index
	int32_t ____index_2;
	// System.Collections.Generic.KeyValuePair`2<TKey,TValue> System.Collections.Generic.Dictionary`2/Enumerator::_current
	KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A ____current_3;
	// System.Int32 System.Collections.Generic.Dictionary`2/Enumerator::_getEnumeratorRetType
	int32_t ____getEnumeratorRetType_4;
};

// System.Delegate
struct Delegate_t  : public RuntimeObject
{
	// System.IntPtr System.Delegate::method_ptr
	intptr_t ___method_ptr_0;
	// System.IntPtr System.Delegate::invoke_impl
	intptr_t ___invoke_impl_1;
	// System.Object System.Delegate::m_target
	RuntimeObject* ___m_target_2;
	// System.IntPtr System.Delegate::method
	intptr_t ___method_3;
	// System.IntPtr System.Delegate::delegate_trampoline
	intptr_t ___delegate_trampoline_4;
	// System.IntPtr System.Delegate::extra_arg
	intptr_t ___extra_arg_5;
	// System.IntPtr System.Delegate::method_code
	intptr_t ___method_code_6;
	// System.IntPtr System.Delegate::interp_method
	intptr_t ___interp_method_7;
	// System.IntPtr System.Delegate::interp_invoke_impl
	intptr_t ___interp_invoke_impl_8;
	// System.Reflection.MethodInfo System.Delegate::method_info
	MethodInfo_t* ___method_info_9;
	// System.Reflection.MethodInfo System.Delegate::original_method_info
	MethodInfo_t* ___original_method_info_10;
	// System.DelegateData System.Delegate::data
	DelegateData_t9B286B493293CD2D23A5B2B5EF0E5B1324C2B77E* ___data_11;
	// System.Boolean System.Delegate::method_is_virtual
	bool ___method_is_virtual_12;
};
// Native definition for P/Invoke marshalling of System.Delegate
struct Delegate_t_marshaled_pinvoke
{
	intptr_t ___method_ptr_0;
	intptr_t ___invoke_impl_1;
	Il2CppIUnknown* ___m_target_2;
	intptr_t ___method_3;
	intptr_t ___delegate_trampoline_4;
	intptr_t ___extra_arg_5;
	intptr_t ___method_code_6;
	intptr_t ___interp_method_7;
	intptr_t ___interp_invoke_impl_8;
	MethodInfo_t* ___method_info_9;
	MethodInfo_t* ___original_method_info_10;
	DelegateData_t9B286B493293CD2D23A5B2B5EF0E5B1324C2B77E* ___data_11;
	int32_t ___method_is_virtual_12;
};
// Native definition for COM marshalling of System.Delegate
struct Delegate_t_marshaled_com
{
	intptr_t ___method_ptr_0;
	intptr_t ___invoke_impl_1;
	Il2CppIUnknown* ___m_target_2;
	intptr_t ___method_3;
	intptr_t ___delegate_trampoline_4;
	intptr_t ___extra_arg_5;
	intptr_t ___method_code_6;
	intptr_t ___interp_method_7;
	intptr_t ___interp_invoke_impl_8;
	MethodInfo_t* ___method_info_9;
	MethodInfo_t* ___original_method_info_10;
	DelegateData_t9B286B493293CD2D23A5B2B5EF0E5B1324C2B77E* ___data_11;
	int32_t ___method_is_virtual_12;
};

// System.Exception
struct Exception_t  : public RuntimeObject
{
	// System.String System.Exception::_className
	String_t* ____className_1;
	// System.String System.Exception::_message
	String_t* ____message_2;
	// System.Collections.IDictionary System.Exception::_data
	RuntimeObject* ____data_3;
	// System.Exception System.Exception::_innerException
	Exception_t* ____innerException_4;
	// System.String System.Exception::_helpURL
	String_t* ____helpURL_5;
	// System.Object System.Exception::_stackTrace
	RuntimeObject* ____stackTrace_6;
	// System.String System.Exception::_stackTraceString
	String_t* ____stackTraceString_7;
	// System.String System.Exception::_remoteStackTraceString
	String_t* ____remoteStackTraceString_8;
	// System.Int32 System.Exception::_remoteStackIndex
	int32_t ____remoteStackIndex_9;
	// System.Object System.Exception::_dynamicMethods
	RuntimeObject* ____dynamicMethods_10;
	// System.Int32 System.Exception::_HResult
	int32_t ____HResult_11;
	// System.String System.Exception::_source
	String_t* ____source_12;
	// System.Runtime.Serialization.SafeSerializationManager System.Exception::_safeSerializationManager
	SafeSerializationManager_tCBB85B95DFD1634237140CD892E82D06ECB3F5E6* ____safeSerializationManager_13;
	// System.Diagnostics.StackTrace[] System.Exception::captured_traces
	StackTraceU5BU5D_t32FBCB20930EAF5BAE3F450FF75228E5450DA0DF* ___captured_traces_14;
	// System.IntPtr[] System.Exception::native_trace_ips
	IntPtrU5BU5D_tFD177F8C806A6921AD7150264CCC62FA00CAD832* ___native_trace_ips_15;
	// System.Int32 System.Exception::caught_in_unmanaged
	int32_t ___caught_in_unmanaged_16;
};
// Native definition for P/Invoke marshalling of System.Exception
struct Exception_t_marshaled_pinvoke
{
	char* ____className_1;
	char* ____message_2;
	RuntimeObject* ____data_3;
	Exception_t_marshaled_pinvoke* ____innerException_4;
	char* ____helpURL_5;
	Il2CppIUnknown* ____stackTrace_6;
	char* ____stackTraceString_7;
	char* ____remoteStackTraceString_8;
	int32_t ____remoteStackIndex_9;
	Il2CppIUnknown* ____dynamicMethods_10;
	int32_t ____HResult_11;
	char* ____source_12;
	SafeSerializationManager_tCBB85B95DFD1634237140CD892E82D06ECB3F5E6* ____safeSerializationManager_13;
	StackTraceU5BU5D_t32FBCB20930EAF5BAE3F450FF75228E5450DA0DF* ___captured_traces_14;
	Il2CppSafeArray/*NONE*/* ___native_trace_ips_15;
	int32_t ___caught_in_unmanaged_16;
};
// Native definition for COM marshalling of System.Exception
struct Exception_t_marshaled_com
{
	Il2CppChar* ____className_1;
	Il2CppChar* ____message_2;
	RuntimeObject* ____data_3;
	Exception_t_marshaled_com* ____innerException_4;
	Il2CppChar* ____helpURL_5;
	Il2CppIUnknown* ____stackTrace_6;
	Il2CppChar* ____stackTraceString_7;
	Il2CppChar* ____remoteStackTraceString_8;
	int32_t ____remoteStackIndex_9;
	Il2CppIUnknown* ____dynamicMethods_10;
	int32_t ____HResult_11;
	Il2CppChar* ____source_12;
	SafeSerializationManager_tCBB85B95DFD1634237140CD892E82D06ECB3F5E6* ____safeSerializationManager_13;
	StackTraceU5BU5D_t32FBCB20930EAF5BAE3F450FF75228E5450DA0DF* ___captured_traces_14;
	Il2CppSafeArray/*NONE*/* ___native_trace_ips_15;
	int32_t ___caught_in_unmanaged_16;
};

// UnityEngine.Object
struct Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C  : public RuntimeObject
{
	// System.IntPtr UnityEngine.Object::m_CachedPtr
	intptr_t ___m_CachedPtr_0;
};
// Native definition for P/Invoke marshalling of UnityEngine.Object
struct Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C_marshaled_pinvoke
{
	intptr_t ___m_CachedPtr_0;
};
// Native definition for COM marshalling of UnityEngine.Object
struct Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C_marshaled_com
{
	intptr_t ___m_CachedPtr_0;
};

// System.RuntimeTypeHandle
struct RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B 
{
	// System.IntPtr System.RuntimeTypeHandle::value
	intptr_t ___value_0;
};

// System.IO.StringReader
struct StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8  : public TextReader_tB8D43017CB6BE1633E5A86D64E7757366507C1F7
{
	// System.String System.IO.StringReader::_s
	String_t* ____s_2;
	// System.Int32 System.IO.StringReader::_pos
	int32_t ____pos_3;
	// System.Int32 System.IO.StringReader::_length
	int32_t ____length_4;
};

// UnityEngine.Component
struct Component_t39FBE53E5EFCF4409111FB22C15FF73717632EC3  : public Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C
{
};

// UnityEngine.GameObject
struct GameObject_t76FEDD663AB33C991A9C9A23129337651094216F  : public Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C
{
};

// System.MulticastDelegate
struct MulticastDelegate_t  : public Delegate_t
{
	// System.Delegate[] System.MulticastDelegate::delegates
	DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* ___delegates_13;
};
// Native definition for P/Invoke marshalling of System.MulticastDelegate
struct MulticastDelegate_t_marshaled_pinvoke : public Delegate_t_marshaled_pinvoke
{
	Delegate_t_marshaled_pinvoke** ___delegates_13;
};
// Native definition for COM marshalling of System.MulticastDelegate
struct MulticastDelegate_t_marshaled_com : public Delegate_t_marshaled_com
{
	Delegate_t_marshaled_com** ___delegates_13;
};

// System.Type
struct Type_t  : public MemberInfo_t
{
	// System.RuntimeTypeHandle System.Type::_impl
	RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B ____impl_8;
};

// System.Action`1<UnityEngine.AndroidJavaObject>
struct Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594  : public MulticastDelegate_t
{
};

// System.Action`1<System.Boolean>
struct Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C  : public MulticastDelegate_t
{
};

// System.Action`1<System.Object>
struct Action_1_t6F9EB113EB3F16226AEF811A2744F4111C116C87  : public MulticastDelegate_t
{
};

// System.Action`1<System.String>
struct Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A  : public MulticastDelegate_t
{
};

// System.Action`2<System.Int32Enum,System.Int32>
struct Action_2_t7B1DA294236CEB03E01D057BD5D4E8DCFCBF1811  : public MulticastDelegate_t
{
};

// System.Action`2<System.Int32Enum,System.Object>
struct Action_2_t829FF86EEA250A853DF3A95B2C4DAC034707E20F  : public MulticastDelegate_t
{
};

// System.Action`2<System.String,System.String>
struct Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D  : public MulticastDelegate_t
{
};

// System.Action`2<RiseSdk.RiseSdk/AFEventType,System.String>
struct Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4  : public MulticastDelegate_t
{
};

// System.Action`2<RiseSdk.RiseSdk/GMSEventType,System.String>
struct Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1  : public MulticastDelegate_t
{
};

// System.Action`2<RiseSdk.RiseSdk/PaymentResult,System.Int32>
struct Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F  : public MulticastDelegate_t
{
};

// System.Action`2<RiseSdk.RiseSdk/SnsEventType,System.Int32>
struct Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6  : public MulticastDelegate_t
{
};

// System.Action`3<System.Boolean,System.Int32,System.Object>
struct Action_3_t2740A37A77E4652A559511D11F6662C28174A796  : public MulticastDelegate_t
{
};

// System.Action`3<System.Boolean,System.Int32,System.String>
struct Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72  : public MulticastDelegate_t
{
};

// System.Action`3<System.Int32,System.Boolean,System.Object>
struct Action_3_tF1ADD96839CDCE68A6D0F397099A1E67C05F263B  : public MulticastDelegate_t
{
};

// System.Action`3<System.Int32,System.Boolean,System.String>
struct Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD  : public MulticastDelegate_t
{
};

// System.Action`4<System.Boolean,System.Boolean,System.Object,System.Object>
struct Action_4_t11E5CF24FE986CDC1E7FA7474E067F17AFB2B206  : public MulticastDelegate_t
{
};

// System.Action`4<System.Boolean,System.Boolean,System.String,System.String>
struct Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA  : public MulticastDelegate_t
{
};

// System.Action`4<System.Int32Enum,System.Int32,System.Object,System.Int32>
struct Action_4_t50E2C24F41BECB3E65BEB8FD155874C71203093C  : public MulticastDelegate_t
{
};

// System.Action`4<RiseSdk.RiseSdk/AdEventType,System.Int32,System.String,System.Int32>
struct Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5  : public MulticastDelegate_t
{
};

// System.Func`1<RiseSdk.RiseSdk>
struct Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39  : public MulticastDelegate_t
{
};

// System.Action
struct Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07  : public MulticastDelegate_t
{
};

// UnityEngine.AndroidJavaRunnable
struct AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F  : public MulticastDelegate_t
{
};

// UnityEngine.Behaviour
struct Behaviour_t01970CFBBA658497AE30F311C447DB0440BAB7FA  : public Component_t39FBE53E5EFCF4409111FB22C15FF73717632EC3
{
};

// UnityEngine.MonoBehaviour
struct MonoBehaviour_t532A11E69716D348D8AA7F854AFCBFCB8AD17F71  : public Behaviour_t01970CFBBA658497AE30F311C447DB0440BAB7FA
{
};

// RiseSdk.RiseSdkListener
struct RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1  : public MonoBehaviour_t532A11E69716D348D8AA7F854AFCBFCB8AD17F71
{
	// System.Single RiseSdk.RiseSdkListener::checkTimer
	float ___checkTimer_20;
};

// <Module>

// <Module>

// System.Collections.Generic.Dictionary`2<System.String,System.Int32>

// System.Collections.Generic.Dictionary`2<System.String,System.Int32>

// System.Collections.Generic.Dictionary`2<System.String,System.Object>

// System.Collections.Generic.Dictionary`2<System.String,System.Object>

// System.Collections.Generic.Dictionary`2<System.String,System.String>

// System.Collections.Generic.Dictionary`2<System.String,System.String>

// System.EmptyArray`1<System.Object>
struct EmptyArray_1_tDF0DD7256B115243AA6BD5558417387A734240EE_StaticFields
{
	// T[] System.EmptyArray`1::Value
	ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ___Value_0;
};

// System.EmptyArray`1<System.Object>

// System.Lazy`1<RiseSdk.RiseSdk>

// System.Lazy`1<RiseSdk.RiseSdk>

// System.Collections.Generic.List`1<System.Object>
struct List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D_StaticFields
{
	// T[] System.Collections.Generic.List`1::s_emptyArray
	ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ___s_emptyArray_5;
};

// System.Collections.Generic.List`1<System.Object>

// UnityEngine.AndroidJavaObject
struct AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_StaticFields
{
	// System.Boolean UnityEngine.AndroidJavaObject::enableDebugPrints
	bool ___enableDebugPrints_0;
};

// UnityEngine.AndroidJavaObject

// System.Collections.ArrayList

// System.Collections.ArrayList

// System.Collections.Hashtable
struct Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_StaticFields
{
	// System.Runtime.CompilerServices.ConditionalWeakTable`2<System.Object,System.Runtime.Serialization.SerializationInfo> System.Collections.Hashtable::s_serializationInfoTable
	ConditionalWeakTable_2_t381B9D0186C0FCC3F83C0696C28C5001468A7858* ___s_serializationInfoTable_21;
};

// System.Collections.Hashtable

// RiseSdk.IRiseSdk

// RiseSdk.IRiseSdk

// MiniJSON
struct MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields
{
	// System.Int32 MiniJSON::lastErrorIndex
	int32_t ___lastErrorIndex_13;
	// System.String MiniJSON::lastDecode
	String_t* ___lastDecode_14;
};

// MiniJSON

// MiniJsonExtensions

// MiniJsonExtensions

// RiseJson

// RiseJson

// System.String
struct String_t_StaticFields
{
	// System.String System.String::Empty
	String_t* ___Empty_6;
};

// System.String

// System.Text.StringBuilder

// System.Text.StringBuilder

// RiseSdk.IRiseSdk/<>c__DisplayClass13_0

// RiseSdk.IRiseSdk/<>c__DisplayClass13_0

// RiseSdk.IRiseSdk/<>c__DisplayClass14_0

// RiseSdk.IRiseSdk/<>c__DisplayClass14_0

// RiseJson/Parser
struct Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_StaticFields
{
	// System.Collections.Generic.Dictionary`2<System.String,System.Int32> RiseJson/Parser::f__switchmap0
	Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* ___f__switchmap0_0;
};

// RiseJson/Parser

// RiseJson/Serializer

// RiseJson/Serializer

// RiseSdk.RiseSdk/<>c
struct U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_StaticFields
{
	// RiseSdk.RiseSdk/<>c RiseSdk.RiseSdk/<>c::<>9
	U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA* ___U3CU3E9_0;
};

// RiseSdk.RiseSdk/<>c

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass12_0

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass12_0

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass16_0

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass16_0

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0

// RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0

// System.Collections.Generic.KeyValuePair`2<System.Object,System.Object>

// System.Collections.Generic.KeyValuePair`2<System.Object,System.Object>

// System.Collections.Generic.KeyValuePair`2<System.String,System.String>

// System.Collections.Generic.KeyValuePair`2<System.String,System.String>

// UnityEngine.AndroidJavaClass

// UnityEngine.AndroidJavaClass

// System.Boolean
struct Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_StaticFields
{
	// System.String System.Boolean::TrueString
	String_t* ___TrueString_5;
	// System.String System.Boolean::FalseString
	String_t* ___FalseString_6;
};

// System.Boolean

// System.Byte

// System.Byte

// System.Char
struct Char_t521A6F19B456D956AF452D926C32709DC03D6B17_StaticFields
{
	// System.Byte[] System.Char::s_categoryForLatin1
	ByteU5BU5D_tA6237BF417AE52AD70CFB4EF24A7A82613DF9031* ___s_categoryForLatin1_3;
};

// System.Char

// System.Decimal
struct Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F_StaticFields
{
	// System.Decimal System.Decimal::Zero
	Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F ___Zero_3;
	// System.Decimal System.Decimal::One
	Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F ___One_4;
	// System.Decimal System.Decimal::MinusOne
	Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F ___MinusOne_5;
	// System.Decimal System.Decimal::MaxValue
	Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F ___MaxValue_6;
	// System.Decimal System.Decimal::MinValue
	Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F ___MinValue_7;
};

// System.Decimal

// System.Double

// System.Double

// System.Int16

// System.Int16

// System.Int32

// System.Int32

// System.Int64

// System.Int64

// System.IntPtr
struct IntPtr_t_StaticFields
{
	// System.IntPtr System.IntPtr::Zero
	intptr_t ___Zero_1;
};

// System.IntPtr

// RiseSdk.RiseSdk
struct RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_StaticFields
{
	// System.Lazy`1<RiseSdk.RiseSdk> RiseSdk.RiseSdk::_instance
	Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0* ____instance_6;
};

// RiseSdk.RiseSdk

// RiseSdk.RiseSdkForAndroid

// RiseSdk.RiseSdkForAndroid

// RiseSdk.RiseSdkForEditor

// RiseSdk.RiseSdkForEditor

// RiseSdk.RiseSdkForIOS

// RiseSdk.RiseSdkForIOS

// System.SByte

// System.SByte

// System.Single

// System.Single

// System.IO.TextReader
struct TextReader_tB8D43017CB6BE1633E5A86D64E7757366507C1F7_StaticFields
{
	// System.IO.TextReader System.IO.TextReader::Null
	TextReader_tB8D43017CB6BE1633E5A86D64E7757366507C1F7* ___Null_1;
};

// System.IO.TextReader

// System.UInt16

// System.UInt16

// System.UInt32

// System.UInt32

// System.UInt64

// System.UInt64

// System.Void

// System.Void

// System.Collections.Generic.Dictionary`2/Enumerator<System.Object,System.Object>

// System.Collections.Generic.Dictionary`2/Enumerator<System.Object,System.Object>

// System.Collections.Generic.Dictionary`2/Enumerator<System.String,System.String>

// System.Collections.Generic.Dictionary`2/Enumerator<System.String,System.String>

// System.Delegate

// System.Delegate

// System.Exception
struct Exception_t_StaticFields
{
	// System.Object System.Exception::s_EDILock
	RuntimeObject* ___s_EDILock_0;
};

// System.Exception

// UnityEngine.Object
struct Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C_StaticFields
{
	// System.Int32 UnityEngine.Object::OffsetOfInstanceIDInCPlusPlusObject
	int32_t ___OffsetOfInstanceIDInCPlusPlusObject_1;
};

// UnityEngine.Object

// System.RuntimeTypeHandle

// System.RuntimeTypeHandle

// System.IO.StringReader

// System.IO.StringReader

// UnityEngine.GameObject

// UnityEngine.GameObject

// System.Type
struct Type_t_StaticFields
{
	// System.Reflection.Binder modreq(System.Runtime.CompilerServices.IsVolatile) System.Type::s_defaultBinder
	Binder_t91BFCE95A7057FADF4D8A1A342AFE52872246235* ___s_defaultBinder_0;
	// System.Char System.Type::Delimiter
	Il2CppChar ___Delimiter_1;
	// System.Type[] System.Type::EmptyTypes
	TypeU5BU5D_t97234E1129B564EB38B8D85CAC2AD8B5B9522FFB* ___EmptyTypes_2;
	// System.Object System.Type::Missing
	RuntimeObject* ___Missing_3;
	// System.Reflection.MemberFilter System.Type::FilterAttribute
	MemberFilter_tF644F1AE82F611B677CE1964D5A3277DDA21D553* ___FilterAttribute_4;
	// System.Reflection.MemberFilter System.Type::FilterName
	MemberFilter_tF644F1AE82F611B677CE1964D5A3277DDA21D553* ___FilterName_5;
	// System.Reflection.MemberFilter System.Type::FilterNameIgnoreCase
	MemberFilter_tF644F1AE82F611B677CE1964D5A3277DDA21D553* ___FilterNameIgnoreCase_6;
};

// System.Type

// System.Action`1<UnityEngine.AndroidJavaObject>

// System.Action`1<UnityEngine.AndroidJavaObject>

// System.Action`1<System.Boolean>

// System.Action`1<System.Boolean>

// System.Action`1<System.Object>

// System.Action`1<System.Object>

// System.Action`1<System.String>

// System.Action`1<System.String>

// System.Action`2<System.Int32Enum,System.Int32>

// System.Action`2<System.Int32Enum,System.Int32>

// System.Action`2<System.Int32Enum,System.Object>

// System.Action`2<System.Int32Enum,System.Object>

// System.Action`2<System.String,System.String>

// System.Action`2<System.String,System.String>

// System.Action`2<RiseSdk.RiseSdk/AFEventType,System.String>

// System.Action`2<RiseSdk.RiseSdk/AFEventType,System.String>

// System.Action`2<RiseSdk.RiseSdk/GMSEventType,System.String>

// System.Action`2<RiseSdk.RiseSdk/GMSEventType,System.String>

// System.Action`2<RiseSdk.RiseSdk/PaymentResult,System.Int32>

// System.Action`2<RiseSdk.RiseSdk/PaymentResult,System.Int32>

// System.Action`2<RiseSdk.RiseSdk/SnsEventType,System.Int32>

// System.Action`2<RiseSdk.RiseSdk/SnsEventType,System.Int32>

// System.Action`3<System.Boolean,System.Int32,System.Object>

// System.Action`3<System.Boolean,System.Int32,System.Object>

// System.Action`3<System.Boolean,System.Int32,System.String>

// System.Action`3<System.Boolean,System.Int32,System.String>

// System.Action`3<System.Int32,System.Boolean,System.Object>

// System.Action`3<System.Int32,System.Boolean,System.Object>

// System.Action`3<System.Int32,System.Boolean,System.String>

// System.Action`3<System.Int32,System.Boolean,System.String>

// System.Action`4<System.Boolean,System.Boolean,System.Object,System.Object>

// System.Action`4<System.Boolean,System.Boolean,System.Object,System.Object>

// System.Action`4<System.Boolean,System.Boolean,System.String,System.String>

// System.Action`4<System.Boolean,System.Boolean,System.String,System.String>

// System.Action`4<System.Int32Enum,System.Int32,System.Object,System.Int32>

// System.Action`4<System.Int32Enum,System.Int32,System.Object,System.Int32>

// System.Action`4<RiseSdk.RiseSdk/AdEventType,System.Int32,System.String,System.Int32>

// System.Action`4<RiseSdk.RiseSdk/AdEventType,System.Int32,System.String,System.Int32>

// System.Func`1<RiseSdk.RiseSdk>

// System.Func`1<RiseSdk.RiseSdk>

// System.Action

// System.Action

// UnityEngine.AndroidJavaRunnable

// UnityEngine.AndroidJavaRunnable

// UnityEngine.Behaviour

// UnityEngine.Behaviour

// UnityEngine.MonoBehaviour

// UnityEngine.MonoBehaviour

// RiseSdk.RiseSdkListener
struct RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields
{
	// System.Action`2<RiseSdk.RiseSdk/PaymentResult,System.Int32> RiseSdk.RiseSdkListener::OnPaymentEvent
	Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* ___OnPaymentEvent_4;
	// System.Action`2<RiseSdk.RiseSdk/SnsEventType,System.Int32> RiseSdk.RiseSdkListener::OnSNSEvent
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* ___OnSNSEvent_5;
	// System.Action`3<System.Boolean,System.Int32,System.String> RiseSdk.RiseSdkListener::OnCacheUrlResult
	Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* ___OnCacheUrlResult_6;
	// System.Action`4<System.Boolean,System.Boolean,System.String,System.String> RiseSdk.RiseSdkListener::OnLeaderBoardEvent
	Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* ___OnLeaderBoardEvent_7;
	// System.Action`3<System.Int32,System.Boolean,System.String> RiseSdk.RiseSdkListener::OnReceiveServerResult
	Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* ___OnReceiveServerResult_8;
	// System.Action`1<System.String> RiseSdk.RiseSdkListener::OnReceivePaymentsPrice
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___OnReceivePaymentsPrice_9;
	// System.Action`1<System.String> RiseSdk.RiseSdkListener::OnReceiveServerExtra
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___OnReceiveServerExtra_10;
	// System.Action`1<System.String> RiseSdk.RiseSdkListener::OnReceiveNotificationData
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___OnReceiveNotificationData_11;
	// System.Action`2<System.String,System.String> RiseSdk.RiseSdkListener::OnDeliciousIconClickEvent
	Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* ___OnDeliciousIconClickEvent_12;
	// System.Action`1<System.String> RiseSdk.RiseSdkListener::appLockEvent
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___appLockEvent_13;
	// System.Action`4<RiseSdk.RiseSdk/AdEventType,System.Int32,System.String,System.Int32> RiseSdk.RiseSdkListener::OnAdEvent
	Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* ___OnAdEvent_14;
	// System.Action RiseSdk.RiseSdkListener::OnResumeAdEvent
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* ___OnResumeAdEvent_15;
	// System.Action`2<RiseSdk.RiseSdk/GMSEventType,System.String> RiseSdk.RiseSdkListener::OnGMSEvent
	Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* ___OnGMSEvent_16;
	// System.Action`2<RiseSdk.RiseSdk/AFEventType,System.String> RiseSdk.RiseSdkListener::OnAFEvent
	Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* ___OnAFEvent_17;
	// System.Action`1<System.Boolean> RiseSdk.RiseSdkListener::OnXsollaLoginEvent
	Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* ___OnXsollaLoginEvent_18;
	// RiseSdk.RiseSdkListener RiseSdk.RiseSdkListener::_instance
	RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* ____instance_19;
	// System.Boolean RiseSdk.RiseSdkListener::signatureCheckOk
	bool ___signatureCheckOk_21;
};

// RiseSdk.RiseSdkListener
#ifdef __clang__
#pragma clang diagnostic pop
#endif
// System.Char[]
struct CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB  : public RuntimeArray
{
	ALIGN_FIELD (8) Il2CppChar m_Items[1];

	inline Il2CppChar GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline Il2CppChar* GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, Il2CppChar value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
	}
	inline Il2CppChar GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline Il2CppChar* GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, Il2CppChar value)
	{
		m_Items[index] = value;
	}
};
// System.Object[]
struct ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918  : public RuntimeArray
{
	ALIGN_FIELD (8) RuntimeObject* m_Items[1];

	inline RuntimeObject* GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline RuntimeObject** GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, RuntimeObject* value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
	inline RuntimeObject* GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline RuntimeObject** GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, RuntimeObject* value)
	{
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
};
// System.Delegate[]
struct DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771  : public RuntimeArray
{
	ALIGN_FIELD (8) Delegate_t* m_Items[1];

	inline Delegate_t* GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline Delegate_t** GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, Delegate_t* value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
	inline Delegate_t* GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline Delegate_t** GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, Delegate_t* value)
	{
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
};
// System.String[]
struct StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248  : public RuntimeArray
{
	ALIGN_FIELD (8) String_t* m_Items[1];

	inline String_t* GetAt(il2cpp_array_size_t index) const
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items[index];
	}
	inline String_t** GetAddressAt(il2cpp_array_size_t index)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		return m_Items + index;
	}
	inline void SetAt(il2cpp_array_size_t index, String_t* value)
	{
		IL2CPP_ARRAY_BOUNDS_CHECK(index, (uint32_t)(this)->max_length);
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
	inline String_t* GetAtUnchecked(il2cpp_array_size_t index) const
	{
		return m_Items[index];
	}
	inline String_t** GetAddressAtUnchecked(il2cpp_array_size_t index)
	{
		return m_Items + index;
	}
	inline void SetAtUnchecked(il2cpp_array_size_t index, String_t* value)
	{
		m_Items[index] = value;
		Il2CppCodeGenWriteBarrier((void**)m_Items + index, (void*)value);
	}
};


// System.Collections.Generic.Dictionary`2/Enumerator<TKey,TValue> System.Collections.Generic.Dictionary`2<System.Object,System.Object>::GetEnumerator()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Enumerator_tEA93FE2B778D098F590CA168BEFC4CD85D73A6B9 Dictionary_2_GetEnumerator_m52AB12790B0B9B46B1DFB1F861C9DBEAB07C1FDA_gshared (Dictionary_2_t14FE4A752A83D53771C584E4C8D14E01F2AFD7BA* __this, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.Dictionary`2/Enumerator<System.Object,System.Object>::Dispose()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Enumerator_Dispose_mEA5E01B81EB943B7003D87CEC1B6040524F0402C_gshared (Enumerator_tEA93FE2B778D098F590CA168BEFC4CD85D73A6B9* __this, const RuntimeMethod* method) ;
// System.Collections.Generic.KeyValuePair`2<TKey,TValue> System.Collections.Generic.Dictionary`2/Enumerator<System.Object,System.Object>::get_Current()
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR KeyValuePair_2_tFC32D2507216293851350D29B64D79F950B55230 Enumerator_get_Current_mE3475384B761E1C7971D3639BD09117FE8363422_gshared_inline (Enumerator_tEA93FE2B778D098F590CA168BEFC4CD85D73A6B9* __this, const RuntimeMethod* method) ;
// TKey System.Collections.Generic.KeyValuePair`2<System.Object,System.Object>::get_Key()
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR RuntimeObject* KeyValuePair_2_get_Key_mBD8EA7557C27E6956F2AF29DA3F7499B2F51A282_gshared_inline (KeyValuePair_2_tFC32D2507216293851350D29B64D79F950B55230* __this, const RuntimeMethod* method) ;
// TValue System.Collections.Generic.KeyValuePair`2<System.Object,System.Object>::get_Value()
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR RuntimeObject* KeyValuePair_2_get_Value_mC6BD8075F9C9DDEF7B4D731E5C38EC19103988E7_gshared_inline (KeyValuePair_2_tFC32D2507216293851350D29B64D79F950B55230* __this, const RuntimeMethod* method) ;
// System.Boolean System.Collections.Generic.Dictionary`2/Enumerator<System.Object,System.Object>::MoveNext()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Enumerator_MoveNext_mCD4950A75FFADD54AF354D48C6C0DB0B5A22A5F4_gshared (Enumerator_tEA93FE2B778D098F590CA168BEFC4CD85D73A6B9* __this, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.List`1<System.Object>::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void List_1__ctor_m7F078BB342729BDF11327FD89D7872265328F690_gshared (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* __this, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.List`1<System.Object>::Add(T)
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void List_1_Add_mEBCF994CC3814631017F46A387B1A192ED6C85C7_gshared_inline (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* __this, RuntimeObject* ___0_item, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.Dictionary`2<System.Object,System.Object>::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Dictionary_2__ctor_m5B32FBC624618211EB461D59CFBB10E987FD1329_gshared (Dictionary_2_t14FE4A752A83D53771C584E4C8D14E01F2AFD7BA* __this, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.Dictionary`2<System.Object,System.Object>::set_Item(TKey,TValue)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Dictionary_2_set_Item_m1A840355E8EDAECEA9D0C6F5E51B248FAA449CBD_gshared (Dictionary_2_t14FE4A752A83D53771C584E4C8D14E01F2AFD7BA* __this, RuntimeObject* ___0_key, RuntimeObject* ___1_value, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.Dictionary`2<System.Object,System.Int32>::.ctor(System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Dictionary_2__ctor_m129B1E1EDDABF00B402C93841CCA7169B8963D83_gshared (Dictionary_2_t5C96F4B6841710A9013966F76224BAE01FB4B4D1* __this, int32_t ___0_capacity, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.Dictionary`2<System.Object,System.Int32>::Add(TKey,TValue)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Dictionary_2_Add_m63897227AFA7035F1772315ABBBE7FD0A250E10C_gshared (Dictionary_2_t5C96F4B6841710A9013966F76224BAE01FB4B4D1* __this, RuntimeObject* ___0_key, int32_t ___1_value, const RuntimeMethod* method) ;
// System.Boolean System.Collections.Generic.Dictionary`2<System.Object,System.Int32>::TryGetValue(TKey,TValue&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Dictionary_2_TryGetValue_m4B8EE45640C70BBFD6F3EFF1040983404C098342_gshared (Dictionary_2_t5C96F4B6841710A9013966F76224BAE01FB4B4D1* __this, RuntimeObject* ___0_key, int32_t* ___1_value, const RuntimeMethod* method) ;
// FieldType UnityEngine.AndroidJavaObject::GetStatic<System.Object>(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* AndroidJavaObject_GetStatic_TisRuntimeObject_m4EF4E4761A0A6E99E0A298F653E8129B1494E4C9_gshared (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, String_t* ___0_fieldName, const RuntimeMethod* method) ;
// System.Void System.Action`1<System.Object>::Invoke(T)
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_1_Invoke_mF2422B2DD29F74CE66F791C3F68E288EC7C3DB9E_gshared_inline (Action_1_t6F9EB113EB3F16226AEF811A2744F4111C116C87* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) ;
// T System.Lazy`1<System.Object>::get_Value()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* Lazy_1_get_Value_mC3D475ED3C0FAB4E8BCC96FBF5EF49ED671B86A4_gshared (Lazy_1_tAD66CD7CC97BB996411F4FE0F49A1817031B906E* __this, const RuntimeMethod* method) ;
// System.Void System.Func`1<System.Object>::.ctor(System.Object,System.IntPtr)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Func_1__ctor_m663374A863E492A515BE9626B6F0E444991834E8_gshared (Func_1_tD5C081AE11746B200C711DD48DBEB00E3A9276D4* __this, RuntimeObject* ___0_object, intptr_t ___1_method, const RuntimeMethod* method) ;
// System.Void System.Lazy`1<System.Object>::.ctor(System.Func`1<T>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Lazy_1__ctor_m4CD0C5ACC0541018DB9BDB090B1EFE67D5A9CEF1_gshared (Lazy_1_tAD66CD7CC97BB996411F4FE0F49A1817031B906E* __this, Func_1_tD5C081AE11746B200C711DD48DBEB00E3A9276D4* ___0_valueFactory, const RuntimeMethod* method) ;
// System.Void System.Action`1<System.Object>::.ctor(System.Object,System.IntPtr)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Action_1__ctor_m2E1DFA67718FC1A0B6E5DFEB78831FFE9C059EB4_gshared (Action_1_t6F9EB113EB3F16226AEF811A2744F4111C116C87* __this, RuntimeObject* ___0_object, intptr_t ___1_method, const RuntimeMethod* method) ;
// T[] System.Array::Empty<System.Object>()
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_gshared_inline (const RuntimeMethod* method) ;
// ReturnType UnityEngine.AndroidJavaObject::CallStatic<System.Boolean>(System.String,System.Object[])
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool AndroidJavaObject_CallStatic_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mE956BC9A30BEC746DE593C53C1B8DB6A685185A6_gshared (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, String_t* ___0_methodName, ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ___1_args, const RuntimeMethod* method) ;
// T UnityEngine.GameObject::AddComponent<System.Object>()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* GameObject_AddComponent_TisRuntimeObject_m69B93700FACCF372F5753371C6E8FB780800B824_gshared (GameObject_t76FEDD663AB33C991A9C9A23129337651094216F* __this, const RuntimeMethod* method) ;
// ReturnType UnityEngine.AndroidJavaObject::Call<System.Object>(System.String,System.Object[])
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* AndroidJavaObject_Call_TisRuntimeObject_mA5AF1A9E0463CE91F0ACB6AC2FE0C1922B579EF7_gshared (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, String_t* ___0_methodName, ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ___1_args, const RuntimeMethod* method) ;
// System.Void System.Action`1<System.Boolean>::Invoke(T)
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_1_Invoke_m69C8773D6967F3B224777183E24EA621CE056F8F_gshared_inline (Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* __this, bool ___0_obj, const RuntimeMethod* method) ;
// System.Void System.Action`2<System.Int32Enum,System.Object>::Invoke(T1,T2)
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_2_Invoke_m05605BFAA1396765DE23112419D8E6F797B65101_gshared_inline (Action_2_t829FF86EEA250A853DF3A95B2C4DAC034707E20F* __this, int32_t ___0_arg1, RuntimeObject* ___1_arg2, const RuntimeMethod* method) ;
// System.Void System.Action`2<System.Int32Enum,System.Int32>::Invoke(T1,T2)
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_2_Invoke_m3BE7DD18C4D59E8D0ECBBA5ED30F084F842415DD_gshared_inline (Action_2_t7B1DA294236CEB03E01D057BD5D4E8DCFCBF1811* __this, int32_t ___0_arg1, int32_t ___1_arg2, const RuntimeMethod* method) ;
// System.Void System.Action`4<System.Boolean,System.Boolean,System.Object,System.Object>::Invoke(T1,T2,T3,T4)
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_4_Invoke_mDCB9B02521F18F1D835EB717E6893B5675F67FEB_gshared_inline (Action_4_t11E5CF24FE986CDC1E7FA7474E067F17AFB2B206* __this, bool ___0_arg1, bool ___1_arg2, RuntimeObject* ___2_arg3, RuntimeObject* ___3_arg4, const RuntimeMethod* method) ;
// System.Void System.Action`3<System.Int32,System.Boolean,System.Object>::Invoke(T1,T2,T3)
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_3_Invoke_m702C8A42A864B27E16DC823C64CDA08C26EA0DB5_gshared_inline (Action_3_tF1ADD96839CDCE68A6D0F397099A1E67C05F263B* __this, int32_t ___0_arg1, bool ___1_arg2, RuntimeObject* ___2_arg3, const RuntimeMethod* method) ;
// System.Void System.Action`3<System.Boolean,System.Int32,System.Object>::Invoke(T1,T2,T3)
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_3_Invoke_mD83B53A0DF001DB829C663514DEB5AC437FEFAF6_gshared_inline (Action_3_t2740A37A77E4652A559511D11F6662C28174A796* __this, bool ___0_arg1, int32_t ___1_arg2, RuntimeObject* ___2_arg3, const RuntimeMethod* method) ;
// System.Void System.Action`4<System.Int32Enum,System.Int32,System.Object,System.Int32>::Invoke(T1,T2,T3,T4)
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_4_Invoke_mF50DBFC8D3619C368487D86A807E3C2205D1433D_gshared_inline (Action_4_t50E2C24F41BECB3E65BEB8FD155874C71203093C* __this, int32_t ___0_arg1, int32_t ___1_arg2, RuntimeObject* ___2_arg3, int32_t ___3_arg4, const RuntimeMethod* method) ;

// System.Char[] System.String::ToCharArray()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* String_ToCharArray_m0699A92AA3E744229EF29CB9D943C47DF4FE5B46 (String_t* __this, const RuntimeMethod* method) ;
// System.Object MiniJSON::parseValue(System.Char[],System.Int32&,System.Boolean&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* MiniJSON_parseValue_m6B209155B5F5B274451B6BCF1B3E4712CF2FA11E (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, bool* ___2_success, const RuntimeMethod* method) ;
// System.Void System.Text.StringBuilder::.ctor(System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void StringBuilder__ctor_m2619CA8D2C3476DF1A302D9D941498BB1C6164C5 (StringBuilder_t* __this, int32_t ___0_capacity, const RuntimeMethod* method) ;
// System.Boolean MiniJSON::serializeValue(System.Object,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_serializeValue_mA5FACD9C89912FCDAEF66E9919E04A67235C1D5A (RuntimeObject* ___0_value, StringBuilder_t* ___1_builder, const RuntimeMethod* method) ;
// System.Int32 System.String::get_Length()
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t String_get_Length_m42625D67623FA5CC7A44D47425CE86FB946542D2_inline (String_t* __this, const RuntimeMethod* method) ;
// System.String System.String::Substring(System.Int32,System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_Substring_mB1D94F47935D22E130FF2C01DBB6A4135FBB76CE (String_t* __this, int32_t ___0_startIndex, int32_t ___1_length, const RuntimeMethod* method) ;
// System.Void System.Collections.Hashtable::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Hashtable__ctor_mD7E2F1EB1BFD683186ECD6EDBE1708AF35C3A87D (Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* __this, const RuntimeMethod* method) ;
// System.Int32 MiniJSON::nextToken(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990 (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) ;
// System.Int32 MiniJSON::lookAhead(System.Char[],System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t MiniJSON_lookAhead_m2CD63C46AD1A3C35A8EF72D1ABB729F324E997C3 (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t ___1_index, const RuntimeMethod* method) ;
// System.String MiniJSON::parseString(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* MiniJSON_parseString_mFFAA593ACB311A5EB76EF61FDAB5CB45BB5D11DC (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) ;
// System.Void System.Collections.ArrayList::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void ArrayList__ctor_m07DC369002304B483B9FC41DBDAF4A25AC3C9F80 (ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* __this, const RuntimeMethod* method) ;
// System.Double MiniJSON::parseNumber(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR double MiniJSON_parseNumber_mC8633E070E7751E9BC8CE00D46D0883E3A14EA61 (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) ;
// System.Collections.Hashtable MiniJSON::parseObject(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* MiniJSON_parseObject_m4EB6104BBF5A992C6F91F008ABC4ED995B83D21C (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) ;
// System.Collections.ArrayList MiniJSON::parseArray(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* MiniJSON_parseArray_m82CFE877BAC4AAD629772733F5317E024054357A (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) ;
// System.Boolean System.Boolean::Parse(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Boolean_Parse_mC9B4406FE9DA904E2B7D75267CAD38E837D39A6D (String_t* ___0_value, const RuntimeMethod* method) ;
// System.Void MiniJSON::eatWhitespace(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void MiniJSON_eatWhitespace_mD6578C429FFFABCE23BFED2D14DD1279B2D2303A (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) ;
// System.String System.String::Concat(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_Concat_m9E3155FB84015C823606188F53B47CB44C444991 (String_t* ___0_str0, String_t* ___1_str1, const RuntimeMethod* method) ;
// System.Void System.Array::Copy(System.Array,System.Int32,System.Array,System.Int32,System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Array_Copy_mB4904E17BD92E320613A3251C0205E0786B3BF41 (RuntimeArray* ___0_sourceArray, int32_t ___1_sourceIndex, RuntimeArray* ___2_destinationArray, int32_t ___3_destinationIndex, int32_t ___4_length, const RuntimeMethod* method) ;
// System.String System.String::CreateString(System.Char[])
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_CreateString_mFBC28D2E3EB87D497F7E702E4FFAD65F635E44DF (String_t* __this, CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_val, const RuntimeMethod* method) ;
// System.UInt32 System.UInt32::Parse(System.String,System.Globalization.NumberStyles)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR uint32_t UInt32_Parse_m5B91BE3C4654E3FA80F55AED00460D7234961E9E (String_t* ___0_s, int32_t ___1_style, const RuntimeMethod* method) ;
// System.String System.Char::ConvertFromUtf32(System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Char_ConvertFromUtf32_m6AC7B7F6371FC03CDD4C42CEC8B9ACE751CE3BC8 (int32_t ___0_utf32, const RuntimeMethod* method) ;
// System.String System.Char::ToString()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Char_ToString_m2A308731F9577C06AF3C0901234E2EAC8327410C (Il2CppChar* __this, const RuntimeMethod* method) ;
// System.Int32 MiniJSON::getLastIndexOfNumber(System.Char[],System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t MiniJSON_getLastIndexOfNumber_mC698C4013038DC3B280F7CCEF78CA899908D97A9 (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t ___1_index, const RuntimeMethod* method) ;
// System.Double System.Double::Parse(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR double Double_Parse_mEBC644CE0C86A405283EC4839F872EF9E556670A (String_t* ___0_s, const RuntimeMethod* method) ;
// System.Int32 System.String::IndexOf(System.Char)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t String_IndexOf_mE21E78F35EF4A7768E385A72814C88D22B689966 (String_t* __this, Il2CppChar ___0_value, const RuntimeMethod* method) ;
// System.Boolean MiniJSON::serializeObject(System.Collections.Hashtable,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_serializeObject_m0242063BB8D2B4C1A0EB136B4EC3E1F7A37948B6 (Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* ___0_anObject, StringBuilder_t* ___1_builder, const RuntimeMethod* method) ;
// System.Boolean MiniJSON::serializeArray(System.Collections.ArrayList,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_serializeArray_m50C5CB05BA9F556F76F9E3275750DADC8F13D5B2 (ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* ___0_anArray, StringBuilder_t* ___1_builder, const RuntimeMethod* method) ;
// System.Text.StringBuilder System.Text.StringBuilder::Append(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR StringBuilder_t* StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D (StringBuilder_t* __this, String_t* ___0_value, const RuntimeMethod* method) ;
// System.Void MiniJSON::serializeString(System.String,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void MiniJSON_serializeString_mF0CB910410E52BC1C0F0E1A30AA0FBF1A1C48B96 (String_t* ___0_aString, StringBuilder_t* ___1_builder, const RuntimeMethod* method) ;
// System.Collections.Generic.Dictionary`2/Enumerator<TKey,TValue> System.Collections.Generic.Dictionary`2<System.String,System.String>::GetEnumerator()
inline Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562 Dictionary_2_GetEnumerator_m46EC45F42CA2279D83568CD3F216AAABA8E749F6 (Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83* __this, const RuntimeMethod* method)
{
	return ((  Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562 (*) (Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83*, const RuntimeMethod*))Dictionary_2_GetEnumerator_m52AB12790B0B9B46B1DFB1F861C9DBEAB07C1FDA_gshared)(__this, method);
}
// System.Void System.Collections.Generic.Dictionary`2/Enumerator<System.String,System.String>::Dispose()
inline void Enumerator_Dispose_m068DDFF5CAFBB15C8A0602DEADA7F10C5BB7ADCD (Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562* __this, const RuntimeMethod* method)
{
	((  void (*) (Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562*, const RuntimeMethod*))Enumerator_Dispose_mEA5E01B81EB943B7003D87CEC1B6040524F0402C_gshared)(__this, method);
}
// System.Collections.Generic.KeyValuePair`2<TKey,TValue> System.Collections.Generic.Dictionary`2/Enumerator<System.String,System.String>::get_Current()
inline KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A Enumerator_get_Current_m49070E88C2E34AB46E6292A3FB1C227576B8506E_inline (Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562* __this, const RuntimeMethod* method)
{
	return ((  KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A (*) (Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562*, const RuntimeMethod*))Enumerator_get_Current_mE3475384B761E1C7971D3639BD09117FE8363422_gshared_inline)(__this, method);
}
// TKey System.Collections.Generic.KeyValuePair`2<System.String,System.String>::get_Key()
inline String_t* KeyValuePair_2_get_Key_m654BCCAE2F20CB11D8E8C2D2C886A0C8A13EB1C4_inline (KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A* __this, const RuntimeMethod* method)
{
	return ((  String_t* (*) (KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A*, const RuntimeMethod*))KeyValuePair_2_get_Key_mBD8EA7557C27E6956F2AF29DA3F7499B2F51A282_gshared_inline)(__this, method);
}
// TValue System.Collections.Generic.KeyValuePair`2<System.String,System.String>::get_Value()
inline String_t* KeyValuePair_2_get_Value_m7345512A32CB4DCAA0643050B18DC8DCD71B927A_inline (KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A* __this, const RuntimeMethod* method)
{
	return ((  String_t* (*) (KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A*, const RuntimeMethod*))KeyValuePair_2_get_Value_mC6BD8075F9C9DDEF7B4D731E5C38EC19103988E7_gshared_inline)(__this, method);
}
// System.Boolean System.Collections.Generic.Dictionary`2/Enumerator<System.String,System.String>::MoveNext()
inline bool Enumerator_MoveNext_mA93491D9B55547D066053F3BC0A69C635F877438 (Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562* __this, const RuntimeMethod* method)
{
	return ((  bool (*) (Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562*, const RuntimeMethod*))Enumerator_MoveNext_mCD4950A75FFADD54AF354D48C6C0DB0B5A22A5F4_gshared)(__this, method);
}
// System.Type System.Object::GetType()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Type_t* Object_GetType_mE10A8FC1E57F3DF29972CCBC026C2DC3942263B3 (RuntimeObject* __this, const RuntimeMethod* method) ;
// System.Boolean System.Type::get_IsArray()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Type_get_IsArray_mB9B8CA713B2AA9D6AFECC24E05AF78D22532B673 (Type_t* __this, const RuntimeMethod* method) ;
// System.Void System.Collections.ArrayList::.ctor(System.Collections.ICollection)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void ArrayList__ctor_mCE5AABB7EF7F41F9170791E2C5145E49684DCE11 (ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* __this, RuntimeObject* ___0_c, const RuntimeMethod* method) ;
// System.String System.Convert::ToString(System.Char)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Convert_ToString_m8270C8361D2796C5C4659D441829CAFEEFBAC91C (Il2CppChar ___0_value, const RuntimeMethod* method) ;
// System.String System.Convert::ToString(System.Decimal)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Convert_ToString_mECE1A0B3DAAA703AA05DD446204669B4B81313AE (Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F ___0_value, const RuntimeMethod* method) ;
// System.Boolean MiniJSON::serializeDictionary(System.Collections.Generic.Dictionary`2<System.String,System.String>,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_serializeDictionary_m99EA287B2265F8C5B00FE3FB0EB294A56BA374DB (Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83* ___0_dict, StringBuilder_t* ___1_builder, const RuntimeMethod* method) ;
// System.Boolean System.Type::get_IsPrimitive()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Type_get_IsPrimitive_m46ACAAB8F754D37713E3E45437705F4F58FAFA18 (Type_t* __this, const RuntimeMethod* method) ;
// System.Double System.Convert::ToDouble(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR double Convert_ToDouble_m86FF4F837721833186E883102C056A35F0860EB0 (RuntimeObject* ___0_value, const RuntimeMethod* method) ;
// System.Void MiniJSON::serializeNumber(System.Double,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void MiniJSON_serializeNumber_mC2747CEB3ABAD1CAE7755B59B6A6737FBE7A66FC (double ___0_number, StringBuilder_t* ___1_builder, const RuntimeMethod* method) ;
// System.Int32 System.Convert::ToInt32(System.Char)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Convert_ToInt32_mDBBE9318A7CCE1560974CE93F5BFED9931CF0052 (Il2CppChar ___0_value, const RuntimeMethod* method) ;
// System.Text.StringBuilder System.Text.StringBuilder::Append(System.Char)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR StringBuilder_t* StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1 (StringBuilder_t* __this, Il2CppChar ___0_value, const RuntimeMethod* method) ;
// System.String System.Convert::ToString(System.Int32,System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Convert_ToString_mC3349029FE37EB00B5BFCB1F87022458A3834E35 (int32_t ___0_value, int32_t ___1_toBase, const RuntimeMethod* method) ;
// System.String System.String::PadLeft(System.Int32,System.Char)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_PadLeft_m99DDD242908E78B71E9631EE66331E8A130EB31F (String_t* __this, int32_t ___0_totalWidth, Il2CppChar ___1_paddingChar, const RuntimeMethod* method) ;
// System.String System.Convert::ToString(System.Double)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Convert_ToString_mA5896BBF6FFE5A37B531E8E2F5258AE6F0C38580 (double ___0_value, const RuntimeMethod* method) ;
// System.Void System.Object::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2 (RuntimeObject* __this, const RuntimeMethod* method) ;
// System.String MiniJSON::jsonEncode(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* MiniJSON_jsonEncode_m65A3BEB6F15FD860F02D7160D6B404AD34DB136F (RuntimeObject* ___0_json, const RuntimeMethod* method) ;
// System.Object MiniJSON::jsonDecode(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* MiniJSON_jsonDecode_m8D73840A67B22A1B7EC331B43E639EEAE4243997 (String_t* ___0_json, const RuntimeMethod* method) ;
// System.Object RiseJson/Parser::Parse(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* Parser_Parse_m4D6309FAC6682FB99C264A5EB0FEDEB4E0F068A1 (String_t* ___0_jsonString, const RuntimeMethod* method) ;
// System.String RiseJson/Serializer::Serialize(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Serializer_Serialize_mC3EB9A1F672CE188D04899BD2E45C7DBDD33E2B8 (RuntimeObject* ___0_obj, const RuntimeMethod* method) ;
// System.Void System.IO.StringReader::.ctor(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void StringReader__ctor_m72556EC1062F49E05CF41B0825AC7FA2DB2A81C0 (StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* __this, String_t* ___0_s, const RuntimeMethod* method) ;
// System.Void System.IO.TextReader::Dispose()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void TextReader_Dispose_mDCB332EFA06970A9CC7EC4596FCC5220B9512616 (TextReader_tB8D43017CB6BE1633E5A86D64E7757366507C1F7* __this, const RuntimeMethod* method) ;
// System.Char RiseJson/Parser::get_PeekChar()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Il2CppChar Parser_get_PeekChar_m896892C8D72C5D3B83B13D29DC10A043A5B0ACEC (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.Void RiseJson/Parser::.ctor(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Parser__ctor_m71515B6F998B14172E3C14C3F05349D764B45B01 (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, String_t* ___0_jsonString, const RuntimeMethod* method) ;
// System.Object RiseJson/Parser::ParseValue()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* Parser_ParseValue_m30DCD24F852EDA784F16ED41833D03EB4C8D9C3F (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.List`1<System.Object>::.ctor()
inline void List_1__ctor_m7F078BB342729BDF11327FD89D7872265328F690 (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* __this, const RuntimeMethod* method)
{
	((  void (*) (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D*, const RuntimeMethod*))List_1__ctor_m7F078BB342729BDF11327FD89D7872265328F690_gshared)(__this, method);
}
// RiseJson/Parser/TOKEN RiseJson/Parser::get_NextSymbol()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Parser_get_NextSymbol_mE4BC8E813CC48B6E5CD7F6C5679AD163723A4E90 (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.Object RiseJson/Parser::ParseBySymbol(RiseJson/Parser/TOKEN)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* Parser_ParseBySymbol_mF0126896D2623317331073FF78E51B6933356B8B (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, int32_t ___0_symbol, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.List`1<System.Object>::Add(T)
inline void List_1_Add_mEBCF994CC3814631017F46A387B1A192ED6C85C7_inline (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* __this, RuntimeObject* ___0_item, const RuntimeMethod* method)
{
	((  void (*) (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D*, RuntimeObject*, const RuntimeMethod*))List_1_Add_mEBCF994CC3814631017F46A387B1A192ED6C85C7_gshared_inline)(__this, ___0_item, method);
}
// System.Collections.Generic.Dictionary`2<System.String,System.Object> RiseJson/Parser::ParseObject()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710* Parser_ParseObject_m97A3AC834623AED937221ADDEA17F7810EE1CB8A (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.Collections.Generic.List`1<System.Object> RiseJson/Parser::ParseArray()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* Parser_ParseArray_mD10428D7BF3D9FA03C0CB739296936A2B6B1FCBA (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.String RiseJson/Parser::ParseString()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Parser_ParseString_m3A36E1EC5CE8E0B3879B83FE7B26027712ABF31C (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.Object RiseJson/Parser::ParseNumber()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* Parser_ParseNumber_m2125CE3D44411D35A69EDC7872224742581EF39C (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.String RiseJson/Parser::get_NextWord()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Parser_get_NextWord_m01A4B42AEFAF4FD146911805717426D50F33266C (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.Boolean System.Int64::TryParse(System.String,System.Int64&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Int64_TryParse_m3FC0128C89CC2331239FC2A0A749BF33455F03D2 (String_t* ___0_s, int64_t* ___1_result, const RuntimeMethod* method) ;
// System.Boolean System.Double::TryParse(System.String,System.Double&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Double_TryParse_m60AD55BC181D70F661BC2A2294E66B5466C3C018 (String_t* ___0_s, double* ___1_result, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.Dictionary`2<System.String,System.Object>::.ctor()
inline void Dictionary_2__ctor_mC4F3DF292BAD88F4BF193C49CD689FAEBC4570A9 (Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710* __this, const RuntimeMethod* method)
{
	((  void (*) (Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710*, const RuntimeMethod*))Dictionary_2__ctor_m5B32FBC624618211EB461D59CFBB10E987FD1329_gshared)(__this, method);
}
// System.Void System.Collections.Generic.Dictionary`2<System.String,System.Object>::set_Item(TKey,TValue)
inline void Dictionary_2_set_Item_m7CCA97075B48AFB2B97E5A072B94BC7679374341 (Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710* __this, String_t* ___0_key, RuntimeObject* ___1_value, const RuntimeMethod* method)
{
	((  void (*) (Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710*, String_t*, RuntimeObject*, const RuntimeMethod*))Dictionary_2_set_Item_m1A840355E8EDAECEA9D0C6F5E51B248FAA449CBD_gshared)(__this, ___0_key, ___1_value, method);
}
// System.Void System.Text.StringBuilder::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void StringBuilder__ctor_m1D99713357DE05DAFA296633639DB55F8C30587D (StringBuilder_t* __this, const RuntimeMethod* method) ;
// System.Char RiseJson/Parser::get_NextChar()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Il2CppChar Parser_get_NextChar_m0BB5BC3A8C52E251EE99E8B3FCA5D205CF66759B (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.Int32 System.Convert::ToInt32(System.String,System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Convert_ToInt32_mD1B3AFBDA26E52D0382434804364FEF8BA241FB4 (String_t* ___0_value, int32_t ___1_fromBase, const RuntimeMethod* method) ;
// System.Char System.Convert::ToChar(System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Il2CppChar Convert_ToChar_mF1B1B205DDEFDE52251235514E7DAFCAB37D1F24 (int32_t ___0_value, const RuntimeMethod* method) ;
// System.Void RiseJson/Parser::EatWhitespace()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Parser_EatWhitespace_m75DEE6C4273E95B5CEDC45964350830BE7C3C79C (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) ;
// System.Void System.Collections.Generic.Dictionary`2<System.String,System.Int32>::.ctor(System.Int32)
inline void Dictionary_2__ctor_mBB2DBA9ECB2AD6046CB4CFB717FDD7E474A439AB (Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* __this, int32_t ___0_capacity, const RuntimeMethod* method)
{
	((  void (*) (Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588*, int32_t, const RuntimeMethod*))Dictionary_2__ctor_m129B1E1EDDABF00B402C93841CCA7169B8963D83_gshared)(__this, ___0_capacity, method);
}
// System.Void System.Collections.Generic.Dictionary`2<System.String,System.Int32>::Add(TKey,TValue)
inline void Dictionary_2_Add_m2FE98C9C3763E31D7CB55207ED3A46B33BF64883 (Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* __this, String_t* ___0_key, int32_t ___1_value, const RuntimeMethod* method)
{
	((  void (*) (Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588*, String_t*, int32_t, const RuntimeMethod*))Dictionary_2_Add_m63897227AFA7035F1772315ABBBE7FD0A250E10C_gshared)(__this, ___0_key, ___1_value, method);
}
// System.Boolean System.Collections.Generic.Dictionary`2<System.String,System.Int32>::TryGetValue(TKey,TValue&)
inline bool Dictionary_2_TryGetValue_m835BB1E6EA8A8BF1242B51E28FD65B43FEF68E2A (Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* __this, String_t* ___0_key, int32_t* ___1_value, const RuntimeMethod* method)
{
	return ((  bool (*) (Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588*, String_t*, int32_t*, const RuntimeMethod*))Dictionary_2_TryGetValue_m4B8EE45640C70BBFD6F3EFF1040983404C098342_gshared)(__this, ___0_key, ___1_value, method);
}
// System.Void RiseJson/Serializer::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer__ctor_mAC17A0B442836479DED00AA2FAF5A86C19A090F6 (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, const RuntimeMethod* method) ;
// System.Void RiseJson/Serializer::SerializeValue(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeValue_mDC148699C7C376EB800E67D037FD6E4DA1B38F2F (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, RuntimeObject* ___0_value, const RuntimeMethod* method) ;
// System.Void RiseJson/Serializer::SerializeString(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeString_m0F09C490477C3813721AFCAE1E9479F60FCA86DB (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, String_t* ___0_str, const RuntimeMethod* method) ;
// System.String System.String::ToLower()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_ToLower_m6191ABA3DC514ED47C10BDA23FD0DDCEAE7ACFBD (String_t* __this, const RuntimeMethod* method) ;
// System.Void RiseJson/Serializer::SerializeArray(System.Collections.IList)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeArray_m458F9C825C7E6B3234D91808251C0B24CA617F08 (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, RuntimeObject* ___0_anArray, const RuntimeMethod* method) ;
// System.Void RiseJson/Serializer::SerializeObject(System.Collections.IDictionary)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeObject_mBE4BB9A6C82762643C1B4B409B3855A838A1895F (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) ;
// System.Void RiseJson/Serializer::SerializeOther(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeOther_m074A35B91C2E7045976EA6FF97098BE737FF3584 (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, RuntimeObject* ___0_value, const RuntimeMethod* method) ;
// System.Void RiseSdk.IRiseSdk/<>c__DisplayClass13_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass13_0__ctor_m38F954BB4142E740723017A0FD28BB04FAF9735E (U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C* __this, const RuntimeMethod* method) ;
// UnityEngine.RuntimePlatform UnityEngine.Application::get_platform()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Application_get_platform_m59EF7D6155D18891B24767F83F388160B1FF2138 (const RuntimeMethod* method) ;
// System.Void UnityEngine.AndroidJavaClass::.ctor(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void AndroidJavaClass__ctor_mB5466169E1151B8CC44C8FED234D79984B431389 (AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* __this, String_t* ___0_className, const RuntimeMethod* method) ;
// FieldType UnityEngine.AndroidJavaObject::GetStatic<UnityEngine.AndroidJavaObject>(System.String)
inline AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, String_t* ___0_fieldName, const RuntimeMethod* method)
{
	return ((  AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* (*) (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0*, String_t*, const RuntimeMethod*))AndroidJavaObject_GetStatic_TisRuntimeObject_m4EF4E4761A0A6E99E0A298F653E8129B1494E4C9_gshared)(__this, ___0_fieldName, method);
}
// System.Void UnityEngine.AndroidJavaRunnable::.ctor(System.Object,System.IntPtr)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void AndroidJavaRunnable__ctor_m000E4FEB2DE8031A1CD733610D76E2BF60490334 (AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F* __this, RuntimeObject* ___0_object, intptr_t ___1_method, const RuntimeMethod* method) ;
// System.Void UnityEngine.AndroidJavaObject::Call(System.String,System.Object[])
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void AndroidJavaObject_Call_mDEF7846E2AB1C5379069BB21049ED55A9D837B1C (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, String_t* ___0_methodName, ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ___1_args, const RuntimeMethod* method) ;
// System.String System.String::Concat(System.String,System.String,System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_Concat_m093934F71A9B351911EE46311674ED463B180006 (String_t* ___0_str0, String_t* ___1_str1, String_t* ___2_str2, String_t* ___3_str3, const RuntimeMethod* method) ;
// System.Void UnityEngine.Debug::LogError(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Debug_LogError_mB00B2B4468EF3CAF041B038D840820FB84C924B2 (RuntimeObject* ___0_message, const RuntimeMethod* method) ;
// System.Void RiseSdk.IRiseSdk/<>c__DisplayClass14_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass14_0__ctor_m11BC8FD83CD5D255A3E6471C016D2506EEABDE42 (U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* __this, const RuntimeMethod* method) ;
// System.Void System.Action::Invoke()
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_Invoke_m7126A54DACA72B845424072887B5F3A51FC3808E_inline (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* __this, const RuntimeMethod* method) ;
// System.Void System.Action`1<UnityEngine.AndroidJavaObject>::Invoke(T)
inline void Action_1_Invoke_mF1A9E047B412B279EA3AC2BFF9E1754B2C45EA56_inline (Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* __this, AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* ___0_obj, const RuntimeMethod* method)
{
	((  void (*) (Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594*, AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0*, const RuntimeMethod*))Action_1_Invoke_mF2422B2DD29F74CE66F791C3F68E288EC7C3DB9E_gshared_inline)(__this, ___0_obj, method);
}
// System.Void RiseSdk.IRiseSdk::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void IRiseSdk__ctor_m1B1BAD2005EB2D6AD68784DDE4E93173E5802F2B (IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* __this, const RuntimeMethod* method) ;
// T System.Lazy`1<RiseSdk.RiseSdk>::get_Value()
inline RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* Lazy_1_get_Value_m1CFEA24735C8452E6D5A843230294721DBAB2033 (Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0* __this, const RuntimeMethod* method)
{
	return ((  RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* (*) (Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0*, const RuntimeMethod*))Lazy_1_get_Value_mC3D475ED3C0FAB4E8BCC96FBF5EF49ED671B86A4_gshared)(__this, method);
}
// RiseSdk.RiseSdkListener RiseSdk.RiseSdkListener::get_Instance()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* RiseSdkListener_get_Instance_m866C1EBF47DE935C0E793F917A8ECA725A4817AE (const RuntimeMethod* method) ;
// System.Void UnityEngine.Behaviour::set_enabled(System.Boolean)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Behaviour_set_enabled_mF1DCFE60EB09E0529FE9476CA804A3AA2D72B16A (Behaviour_t01970CFBBA658497AE30F311C447DB0440BAB7FA* __this, bool ___0_value, const RuntimeMethod* method) ;
// System.Void RiseSdk.RiseSdkForAndroid::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid__ctor_m01D8A1FB5527EB80DB0546B1410761BB485DF8E3 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) ;
// System.String System.Int32::ToString()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Int32_ToString_m030E01C24E294D6762FB0B6F37CB541581F55CA5 (int32_t* __this, const RuntimeMethod* method) ;
// System.Void UnityEngine.Debug::Log(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB (RuntimeObject* ___0_message, const RuntimeMethod* method) ;
// System.Void System.Func`1<RiseSdk.RiseSdk>::.ctor(System.Object,System.IntPtr)
inline void Func_1__ctor_m573904768C47610B49696F3E8A4A9E15D206594A (Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39* __this, RuntimeObject* ___0_object, intptr_t ___1_method, const RuntimeMethod* method)
{
	((  void (*) (Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39*, RuntimeObject*, intptr_t, const RuntimeMethod*))Func_1__ctor_m663374A863E492A515BE9626B6F0E444991834E8_gshared)(__this, ___0_object, ___1_method, method);
}
// System.Void System.Lazy`1<RiseSdk.RiseSdk>::.ctor(System.Func`1<T>)
inline void Lazy_1__ctor_mDA25196CB38DB23B1A01521353908954A84CDEDA (Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0* __this, Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39* ___0_valueFactory, const RuntimeMethod* method)
{
	((  void (*) (Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0*, Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39*, const RuntimeMethod*))Lazy_1__ctor_m4CD0C5ACC0541018DB9BDB090B1EFE67D5A9CEF1_gshared)(__this, ___0_valueFactory, method);
}
// System.Void RiseSdk.RiseSdk/<>c::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__ctor_m001E179ABE86775932652250A7338E2FB003B3FB (U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA* __this, const RuntimeMethod* method) ;
// System.Void RiseSdk.RiseSdk::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk__ctor_mDE9BBB5411651A1A94F838AA27F91641B547793E (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, const RuntimeMethod* method) ;
// System.IntPtr UnityEngine.AndroidJavaObject::GetRawClass()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR intptr_t AndroidJavaObject_GetRawClass_mE4FB4DC4F856A52E10C6AAD0B65BEBF47B5071F5 (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, const RuntimeMethod* method) ;
// System.String System.IntPtr::ToString()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* IntPtr_ToString_mDA1150F4802E1FB9313FB4E32C684EEE19116E5C (intptr_t* __this, const RuntimeMethod* method) ;
// System.Boolean System.String::Equals(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool String_Equals_mCD5F35DEDCAFE51ACD4E033726FC2EF8DF7E9B4D (String_t* __this, String_t* ___0_value, const RuntimeMethod* method) ;
// System.Void System.Action`1<UnityEngine.AndroidJavaObject>::.ctor(System.Object,System.IntPtr)
inline void Action_1__ctor_mAD4F64E1D47B76FC413CEBA12F172E968C83AD04 (Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* __this, RuntimeObject* ___0_object, intptr_t ___1_method, const RuntimeMethod* method)
{
	((  void (*) (Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594*, RuntimeObject*, intptr_t, const RuntimeMethod*))Action_1__ctor_m2E1DFA67718FC1A0B6E5DFEB78831FFE9C059EB4_gshared)(__this, ___0_object, ___1_method, method);
}
// System.Void RiseSdk.IRiseSdk::CallSafeOnMainThread2(System.Action`1<UnityEngine.AndroidJavaObject>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void IRiseSdk_CallSafeOnMainThread2_mC76A4339B51170DA0BE042688ED53D5F3EBCEDAA (IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* __this, Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* ___0_action, const RuntimeMethod* method) ;
// System.Void System.Action::.ctor(System.Object,System.IntPtr)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* __this, RuntimeObject* ___0_object, intptr_t ___1_method, const RuntimeMethod* method) ;
// System.Void RiseSdk.IRiseSdk::CallSafeOnMainThread(System.Action)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57 (IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* __this, Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* ___0_action, const RuntimeMethod* method) ;
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass9_0__ctor_m4E08A045DF1F294C589299B9E4C206EB557B91BB (U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D* __this, const RuntimeMethod* method) ;
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass10_0__ctor_m99B4257ECBAFE4E1665ACEAE43D93CFD88337F2F (U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D* __this, const RuntimeMethod* method) ;
// UnityEngine.AndroidJavaClass RiseSdk.RiseSdkForAndroid::get__javaClass()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) ;
// T[] System.Array::Empty<System.Object>()
inline ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_inline (const RuntimeMethod* method)
{
	return ((  ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* (*) (const RuntimeMethod*))Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_gshared_inline)(method);
}
// ReturnType UnityEngine.AndroidJavaObject::CallStatic<System.Boolean>(System.String,System.Object[])
inline bool AndroidJavaObject_CallStatic_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mE956BC9A30BEC746DE593C53C1B8DB6A685185A6 (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, String_t* ___0_methodName, ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ___1_args, const RuntimeMethod* method)
{
	return ((  bool (*) (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0*, String_t*, ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*, const RuntimeMethod*))AndroidJavaObject_CallStatic_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mE956BC9A30BEC746DE593C53C1B8DB6A685185A6_gshared)(__this, ___0_methodName, ___1_args, method);
}
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass12_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass12_0__ctor_m9F65A8111F1102D7AA6D272EA90DF0542FFF4694 (U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76* __this, const RuntimeMethod* method) ;
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass13_0__ctor_m9FA6F3648E6C607FBBC42BFADB46E0F5523C3F67 (U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D* __this, const RuntimeMethod* method) ;
// System.String System.String::Format(System.String,System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_Format_mA8DBB4C2516B9723C5A41E6CB1E2FAF4BBE96DD8 (String_t* ___0_format, RuntimeObject* ___1_arg0, const RuntimeMethod* method) ;
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass16_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass16_0__ctor_m0A7A31947CA27FDF4CD4DB3E89B70B5B059DE832 (U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA* __this, const RuntimeMethod* method) ;
// System.Void UnityEngine.AndroidJavaObject::CallStatic(System.String,System.Object[])
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, String_t* ___0_methodName, ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ___1_args, const RuntimeMethod* method) ;
// System.Int32 UnityEngine.Random::Range(System.Int32,System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Random_Range_m6763D9767F033357F88B6637F048F4ACA4123B68 (int32_t ___0_minInclusive, int32_t ___1_maxExclusive, const RuntimeMethod* method) ;
// System.String System.String::Format(System.String,System.Object,System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987 (String_t* ___0_format, RuntimeObject* ___1_arg0, RuntimeObject* ___2_arg1, const RuntimeMethod* method) ;
// System.Delegate System.Delegate::Combine(System.Delegate,System.Delegate)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Delegate_t* Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00 (Delegate_t* ___0_a, Delegate_t* ___1_b, const RuntimeMethod* method) ;
// System.Delegate System.Delegate::Remove(System.Delegate,System.Delegate)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Delegate_t* Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3 (Delegate_t* ___0_source, Delegate_t* ___1_value, const RuntimeMethod* method) ;
// System.Boolean UnityEngine.Object::op_Equality(UnityEngine.Object,UnityEngine.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Object_op_Equality_mB6120F782D83091EF56A198FCEBCF066DB4A9605 (Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C* ___0_x, Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C* ___1_y, const RuntimeMethod* method) ;
// System.Type System.Type::GetTypeFromHandle(System.RuntimeTypeHandle)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Type_t* Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57 (RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B ___0_handle, const RuntimeMethod* method) ;
// UnityEngine.Object UnityEngine.Object::FindObjectOfType(System.Type)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C* Object_FindObjectOfType_mECCF3627FE53B9B53B5D92BFB9FA88490B0ACD5E (Type_t* ___0_type, const RuntimeMethod* method) ;
// System.Void UnityEngine.GameObject::.ctor(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void GameObject__ctor_m37D512B05D292F954792225E6C6EEE95293A9B88 (GameObject_t76FEDD663AB33C991A9C9A23129337651094216F* __this, String_t* ___0_name, const RuntimeMethod* method) ;
// T UnityEngine.GameObject::AddComponent<RiseSdk.RiseSdkListener>()
inline RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* GameObject_AddComponent_TisRiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_mBBD0626F5E8E30C9E5DE7AF8642C92E276F70A26 (GameObject_t76FEDD663AB33C991A9C9A23129337651094216F* __this, const RuntimeMethod* method)
{
	return ((  RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* (*) (GameObject_t76FEDD663AB33C991A9C9A23129337651094216F*, const RuntimeMethod*))GameObject_AddComponent_TisRuntimeObject_m69B93700FACCF372F5753371C6E8FB780800B824_gshared)(__this, method);
}
// System.Void UnityEngine.Object::DontDestroyOnLoad(UnityEngine.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Object_DontDestroyOnLoad_m4B70C3AEF886C176543D1295507B6455C9DCAEA7 (Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C* ___0_target, const RuntimeMethod* method) ;
// RiseSdk.RiseSdk RiseSdk.RiseSdk::get_Instance()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* RiseSdk_get_Instance_mF7B692D4BF44E697C87CCC5FA0ECCA2C846F54E2 (const RuntimeMethod* method) ;
// System.Void System.Action`1<System.String>::Invoke(T)
inline void Action_1_Invoke_m690438AAE38F9762172E3AE0A33D0B42ACD35790_inline (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* __this, String_t* ___0_obj, const RuntimeMethod* method)
{
	((  void (*) (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*, String_t*, const RuntimeMethod*))Action_1_Invoke_mF2422B2DD29F74CE66F791C3F68E288EC7C3DB9E_gshared_inline)(__this, ___0_obj, method);
}
// System.Single UnityEngine.Time::get_deltaTime()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR float Time_get_deltaTime_mC3195000401F0FD167DD2F948FD2BC58330D0865 (const RuntimeMethod* method) ;
// System.Void RiseSdk.RiseSdkListener::Check()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_Check_m4E6C4AB847C12B0564AEB66D3E675A3116263132 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, const RuntimeMethod* method) ;
// System.Void UnityEngine.Application::Quit()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Application_Quit_mE304382DB9A6455C2A474C8F364C7387F37E9281 (const RuntimeMethod* method) ;
// ReturnType UnityEngine.AndroidJavaObject::Call<UnityEngine.AndroidJavaObject>(System.String,System.Object[])
inline AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* AndroidJavaObject_Call_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_m020246E0988293B6126B690BD6CE4D894276AA3D (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, String_t* ___0_methodName, ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* ___1_args, const RuntimeMethod* method)
{
	return ((  AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* (*) (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0*, String_t*, ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*, const RuntimeMethod*))AndroidJavaObject_Call_TisRuntimeObject_mA5AF1A9E0463CE91F0ACB6AC2FE0C1922B579EF7_gshared)(__this, ___0_methodName, ___1_args, method);
}
// System.IntPtr UnityEngine.AndroidJavaObject::GetRawObject()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR intptr_t AndroidJavaObject_GetRawObject_m536F043B5CE2C21369FF6173C9D2A9A62136BC48 (AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* __this, const RuntimeMethod* method) ;
// System.Int32 RiseSdk.RiseSdkListener::E77ITgnNHHS(System.IntPtr,System.IntPtr)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t RiseSdkListener_E77ITgnNHHS_mBA8AA59A548404DD89A5584213E855A548B38DD4 (intptr_t ___0_ptr, intptr_t ___1_obj, const RuntimeMethod* method) ;
// System.Boolean System.String::op_Equality(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool String_op_Equality_m030E1B219352228970A076136E455C4E568C02C1 (String_t* ___0_a, String_t* ___1_b, const RuntimeMethod* method) ;
// System.Void System.Action`1<System.Boolean>::Invoke(T)
inline void Action_1_Invoke_m69C8773D6967F3B224777183E24EA621CE056F8F_inline (Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* __this, bool ___0_obj, const RuntimeMethod* method)
{
	((  void (*) (Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C*, bool, const RuntimeMethod*))Action_1_Invoke_m69C8773D6967F3B224777183E24EA621CE056F8F_gshared_inline)(__this, ___0_obj, method);
}
// System.Void System.Action`2<RiseSdk.RiseSdk/GMSEventType,System.String>::Invoke(T1,T2)
inline void Action_2_Invoke_m1706876FE6BBB886C80DF6E3C606854C525844F9_inline (Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* __this, int32_t ___0_arg1, String_t* ___1_arg2, const RuntimeMethod* method)
{
	((  void (*) (Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1*, int32_t, String_t*, const RuntimeMethod*))Action_2_Invoke_m05605BFAA1396765DE23112419D8E6F797B65101_gshared_inline)(__this, ___0_arg1, ___1_arg2, method);
}
// System.Void System.Action`2<RiseSdk.RiseSdk/AFEventType,System.String>::Invoke(T1,T2)
inline void Action_2_Invoke_mF6A889F5B1A87B716BC53950D781FA1708CB54A9_inline (Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* __this, int32_t ___0_arg1, String_t* ___1_arg2, const RuntimeMethod* method)
{
	((  void (*) (Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4*, int32_t, String_t*, const RuntimeMethod*))Action_2_Invoke_m05605BFAA1396765DE23112419D8E6F797B65101_gshared_inline)(__this, ___0_arg1, ___1_arg2, method);
}
// System.Int32 System.Int32::Parse(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0 (String_t* ___0_s, const RuntimeMethod* method) ;
// System.Void System.Action`2<RiseSdk.RiseSdk/PaymentResult,System.Int32>::Invoke(T1,T2)
inline void Action_2_Invoke_m1734278892DDE9FFC7FE67FAC65971B69808C74C_inline (Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* __this, int32_t ___0_arg1, int32_t ___1_arg2, const RuntimeMethod* method)
{
	((  void (*) (Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F*, int32_t, int32_t, const RuntimeMethod*))Action_2_Invoke_m3BE7DD18C4D59E8D0ECBBA5ED30F084F842415DD_gshared_inline)(__this, ___0_arg1, ___1_arg2, method);
}
// System.Void System.Action`2<RiseSdk.RiseSdk/SnsEventType,System.Int32>::Invoke(T1,T2)
inline void Action_2_Invoke_m55AB25A539E14B5A8AB23805B6406481EF687FA7_inline (Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* __this, int32_t ___0_arg1, int32_t ___1_arg2, const RuntimeMethod* method)
{
	((  void (*) (Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6*, int32_t, int32_t, const RuntimeMethod*))Action_2_Invoke_m3BE7DD18C4D59E8D0ECBBA5ED30F084F842415DD_gshared_inline)(__this, ___0_arg1, ___1_arg2, method);
}
// System.Void System.Action`4<System.Boolean,System.Boolean,System.String,System.String>::Invoke(T1,T2,T3,T4)
inline void Action_4_Invoke_m9D80EF97751363EE2BBBEDA48A47B537CAE8C3A5_inline (Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* __this, bool ___0_arg1, bool ___1_arg2, String_t* ___2_arg3, String_t* ___3_arg4, const RuntimeMethod* method)
{
	((  void (*) (Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA*, bool, bool, String_t*, String_t*, const RuntimeMethod*))Action_4_Invoke_mDCB9B02521F18F1D835EB717E6893B5675F67FEB_gshared_inline)(__this, ___0_arg1, ___1_arg2, ___2_arg3, ___3_arg4, method);
}
// System.String[] System.String::Split(System.Char,System.StringSplitOptions)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4 (String_t* __this, Il2CppChar ___0_separator, int32_t ___1_options, const RuntimeMethod* method) ;
// System.Void System.Action`3<System.Int32,System.Boolean,System.String>::Invoke(T1,T2,T3)
inline void Action_3_Invoke_m1A8B27464F12A8FBBCB0D6B556E9C42DBCB33263_inline (Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* __this, int32_t ___0_arg1, bool ___1_arg2, String_t* ___2_arg3, const RuntimeMethod* method)
{
	((  void (*) (Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD*, int32_t, bool, String_t*, const RuntimeMethod*))Action_3_Invoke_m702C8A42A864B27E16DC823C64CDA08C26EA0DB5_gshared_inline)(__this, ___0_arg1, ___1_arg2, ___2_arg3, method);
}
// System.Void System.Action`3<System.Boolean,System.Int32,System.String>::Invoke(T1,T2,T3)
inline void Action_3_Invoke_m397F612018E5E6EA02DEBA300CDA0E0E3B3FC8EB_inline (Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* __this, bool ___0_arg1, int32_t ___1_arg2, String_t* ___2_arg3, const RuntimeMethod* method)
{
	((  void (*) (Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72*, bool, int32_t, String_t*, const RuntimeMethod*))Action_3_Invoke_mD83B53A0DF001DB829C663514DEB5AC437FEFAF6_gshared_inline)(__this, ___0_arg1, ___1_arg2, ___2_arg3, method);
}
// System.Boolean System.String::IsNullOrEmpty(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478 (String_t* ___0_value, const RuntimeMethod* method) ;
// System.Void System.Action`4<RiseSdk.RiseSdk/AdEventType,System.Int32,System.String,System.Int32>::Invoke(T1,T2,T3,T4)
inline void Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline (Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* __this, int32_t ___0_arg1, int32_t ___1_arg2, String_t* ___2_arg3, int32_t ___3_arg4, const RuntimeMethod* method)
{
	((  void (*) (Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5*, int32_t, int32_t, String_t*, int32_t, const RuntimeMethod*))Action_4_Invoke_mF50DBFC8D3619C368487D86A807E3C2205D1433D_gshared_inline)(__this, ___0_arg1, ___1_arg2, ___2_arg3, ___3_arg4, method);
}
// System.Boolean System.Int32::TryParse(System.String,System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool Int32_TryParse_mC928DE2FEC1C35ED5298BDDCA9868076E94B8A21 (String_t* ___0_s, int32_t* ___1_result, const RuntimeMethod* method) ;
// System.Void UnityEngine.MonoBehaviour::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void MonoBehaviour__ctor_m592DB0105CA0BC97AA1C5F4AD27B12D68A3B7C1E (MonoBehaviour_t532A11E69716D348D8AA7F854AFCBFCB8AD17F71* __this, const RuntimeMethod* method) ;
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Object MiniJSON::jsonDecode(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* MiniJSON_jsonDecode_m8D73840A67B22A1B7EC331B43E639EEAE4243997 (String_t* ___0_json, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	bool V_1 = false;
	RuntimeObject* G_B3_0 = NULL;
	RuntimeObject* G_B2_0 = NULL;
	{
		// MiniJSON.lastDecode = json;
		String_t* L_0 = ___0_json;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastDecode_14 = L_0;
		Il2CppCodeGenWriteBarrier((void**)(&((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastDecode_14), (void*)L_0);
		// if (json != null)
		String_t* L_1 = ___0_json;
		if (!L_1)
		{
			goto IL_002d;
		}
	}
	{
		// char[] charArray = json.ToCharArray();
		String_t* L_2 = ___0_json;
		NullCheck(L_2);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_3;
		L_3 = String_ToCharArray_m0699A92AA3E744229EF29CB9D943C47DF4FE5B46(L_2, NULL);
		// int index = 0;
		V_0 = 0;
		// bool success = true;
		V_1 = (bool)1;
		// object value = MiniJSON.parseValue(charArray, ref index, ref success);
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		RuntimeObject* L_4;
		L_4 = MiniJSON_parseValue_m6B209155B5F5B274451B6BCF1B3E4712CF2FA11E(L_3, (&V_0), (&V_1), NULL);
		// if (success)
		bool L_5 = V_1;
		G_B2_0 = L_4;
		if (!L_5)
		{
			G_B3_0 = L_4;
			goto IL_0026;
		}
	}
	{
		// MiniJSON.lastErrorIndex = -1;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastErrorIndex_13 = (-1);
		return G_B2_0;
	}

IL_0026:
	{
		// MiniJSON.lastErrorIndex = index;
		int32_t L_6 = V_0;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastErrorIndex_13 = L_6;
		// return value;
		return G_B3_0;
	}

IL_002d:
	{
		// return null;
		return NULL;
	}
}
// System.String MiniJSON::jsonEncode(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* MiniJSON_jsonEncode_m65A3BEB6F15FD860F02D7160D6B404AD34DB136F (RuntimeObject* ___0_json, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&StringBuilder_t_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	StringBuilder_t* V_0 = NULL;
	{
		// var builder = new StringBuilder(BUILDER_CAPACITY);
		StringBuilder_t* L_0 = (StringBuilder_t*)il2cpp_codegen_object_new(StringBuilder_t_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		StringBuilder__ctor_m2619CA8D2C3476DF1A302D9D941498BB1C6164C5(L_0, ((int32_t)2000), NULL);
		V_0 = L_0;
		// var success = MiniJSON.serializeValue(json, builder);
		RuntimeObject* L_1 = ___0_json;
		StringBuilder_t* L_2 = V_0;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		bool L_3;
		L_3 = MiniJSON_serializeValue_mA5FACD9C89912FCDAEF66E9919E04A67235C1D5A(L_1, L_2, NULL);
		// return (success ? builder.ToString() : null);
		if (L_3)
		{
			goto IL_0016;
		}
	}
	{
		return (String_t*)NULL;
	}

IL_0016:
	{
		StringBuilder_t* L_4 = V_0;
		NullCheck(L_4);
		String_t* L_5;
		L_5 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_4);
		return L_5;
	}
}
// System.Boolean MiniJSON::lastDecodeSuccessful()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_lastDecodeSuccessful_m7292121850E346061A862D0005954144939C430D (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// return (MiniJSON.lastErrorIndex == -1);
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_0 = ((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastErrorIndex_13;
		return (bool)((((int32_t)L_0) == ((int32_t)(-1)))? 1 : 0);
	}
}
// System.Int32 MiniJSON::getLastErrorIndex()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t MiniJSON_getLastErrorIndex_m6872BA824032B41767FBCDF3674E4389E821DF07 (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// return MiniJSON.lastErrorIndex;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_0 = ((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastErrorIndex_13;
		return L_0;
	}
}
// System.String MiniJSON::getLastErrorSnippet()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* MiniJSON_getLastErrorSnippet_m30B5B8C7DFEA8CD43180BD4D03059741B5290CCA (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	int32_t V_1 = 0;
	{
		// if (MiniJSON.lastErrorIndex == -1)
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_0 = ((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastErrorIndex_13;
		if ((!(((uint32_t)L_0) == ((uint32_t)(-1)))))
		{
			goto IL_000e;
		}
	}
	{
		// return "";
		return _stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709;
	}

IL_000e:
	{
		// int startIndex = MiniJSON.lastErrorIndex - 5;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_1 = ((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastErrorIndex_13;
		V_0 = ((int32_t)il2cpp_codegen_subtract(L_1, 5));
		// int endIndex = MiniJSON.lastErrorIndex + 15;
		int32_t L_2 = ((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastErrorIndex_13;
		V_1 = ((int32_t)il2cpp_codegen_add(L_2, ((int32_t)15)));
		// if (startIndex < 0)
		int32_t L_3 = V_0;
		if ((((int32_t)L_3) >= ((int32_t)0)))
		{
			goto IL_0025;
		}
	}
	{
		// startIndex = 0;
		V_0 = 0;
	}

IL_0025:
	{
		// if (endIndex >= MiniJSON.lastDecode.Length)
		int32_t L_4 = V_1;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		String_t* L_5 = ((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastDecode_14;
		NullCheck(L_5);
		int32_t L_6;
		L_6 = String_get_Length_m42625D67623FA5CC7A44D47425CE86FB946542D2_inline(L_5, NULL);
		if ((((int32_t)L_4) < ((int32_t)L_6)))
		{
			goto IL_003f;
		}
	}
	{
		// endIndex = MiniJSON.lastDecode.Length - 1;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		String_t* L_7 = ((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastDecode_14;
		NullCheck(L_7);
		int32_t L_8;
		L_8 = String_get_Length_m42625D67623FA5CC7A44D47425CE86FB946542D2_inline(L_7, NULL);
		V_1 = ((int32_t)il2cpp_codegen_subtract(L_8, 1));
	}

IL_003f:
	{
		// return MiniJSON.lastDecode.Substring(startIndex, endIndex - startIndex + 1);
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		String_t* L_9 = ((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastDecode_14;
		int32_t L_10 = V_0;
		int32_t L_11 = V_1;
		int32_t L_12 = V_0;
		NullCheck(L_9);
		String_t* L_13;
		L_13 = String_Substring_mB1D94F47935D22E130FF2C01DBB6A4135FBB76CE(L_9, L_10, ((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_subtract(L_11, L_12)), 1)), NULL);
		return L_13;
	}
}
// System.Collections.Hashtable MiniJSON::parseObject(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* MiniJSON_parseObject_m4EB6104BBF5A992C6F91F008ABC4ED995B83D21C (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* V_0 = NULL;
	int32_t V_1 = 0;
	bool V_2 = false;
	String_t* V_3 = NULL;
	bool V_4 = false;
	RuntimeObject* V_5 = NULL;
	{
		// Hashtable table = new Hashtable();
		Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* L_0 = (Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)il2cpp_codegen_object_new(Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		Hashtable__ctor_mD7E2F1EB1BFD683186ECD6EDBE1708AF35C3A87D(L_0, NULL);
		V_0 = L_0;
		// nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_1 = ___0_json;
		int32_t* L_2 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_3;
		L_3 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_1, L_2, NULL);
		// bool done = false;
		V_2 = (bool)0;
		goto IL_0074;
	}

IL_0012:
	{
		// token = lookAhead(json, index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_4 = ___0_json;
		int32_t* L_5 = ___1_index;
		int32_t L_6 = *((int32_t*)L_5);
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_7;
		L_7 = MiniJSON_lookAhead_m2CD63C46AD1A3C35A8EF72D1ABB729F324E997C3(L_4, L_6, NULL);
		V_1 = L_7;
		// if (token == MiniJSON.TOKEN_NONE)
		int32_t L_8 = V_1;
		if (L_8)
		{
			goto IL_0020;
		}
	}
	{
		// return null;
		return (Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)NULL;
	}

IL_0020:
	{
		// else if (token == MiniJSON.TOKEN_COMMA)
		int32_t L_9 = V_1;
		if ((!(((uint32_t)L_9) == ((uint32_t)6))))
		{
			goto IL_002e;
		}
	}
	{
		// nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_10 = ___0_json;
		int32_t* L_11 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_12;
		L_12 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_10, L_11, NULL);
		goto IL_0074;
	}

IL_002e:
	{
		// else if (token == MiniJSON.TOKEN_CURLY_CLOSE)
		int32_t L_13 = V_1;
		if ((!(((uint32_t)L_13) == ((uint32_t)2))))
		{
			goto IL_003c;
		}
	}
	{
		// nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_14 = ___0_json;
		int32_t* L_15 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_16;
		L_16 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_14, L_15, NULL);
		// return table;
		Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* L_17 = V_0;
		return L_17;
	}

IL_003c:
	{
		// string name = parseString(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_18 = ___0_json;
		int32_t* L_19 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		String_t* L_20;
		L_20 = MiniJSON_parseString_mFFAA593ACB311A5EB76EF61FDAB5CB45BB5D11DC(L_18, L_19, NULL);
		V_3 = L_20;
		// if (name == null)
		String_t* L_21 = V_3;
		if (L_21)
		{
			goto IL_0049;
		}
	}
	{
		// return null;
		return (Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)NULL;
	}

IL_0049:
	{
		// token = nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_22 = ___0_json;
		int32_t* L_23 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_24;
		L_24 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_22, L_23, NULL);
		V_1 = L_24;
		// if (token != MiniJSON.TOKEN_COLON)
		int32_t L_25 = V_1;
		if ((((int32_t)L_25) == ((int32_t)5)))
		{
			goto IL_0057;
		}
	}
	{
		// return null;
		return (Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)NULL;
	}

IL_0057:
	{
		// bool success = true;
		V_4 = (bool)1;
		// object value = parseValue(json, ref index, ref success);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_26 = ___0_json;
		int32_t* L_27 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		RuntimeObject* L_28;
		L_28 = MiniJSON_parseValue_m6B209155B5F5B274451B6BCF1B3E4712CF2FA11E(L_26, L_27, (&V_4), NULL);
		V_5 = L_28;
		// if (!success)
		bool L_29 = V_4;
		if (L_29)
		{
			goto IL_006b;
		}
	}
	{
		// return null;
		return (Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)NULL;
	}

IL_006b:
	{
		// table[name] = value;
		Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* L_30 = V_0;
		String_t* L_31 = V_3;
		RuntimeObject* L_32 = V_5;
		NullCheck(L_30);
		VirtualActionInvoker2< RuntimeObject*, RuntimeObject* >::Invoke(22 /* System.Void System.Collections.Hashtable::set_Item(System.Object,System.Object) */, L_30, L_31, L_32);
	}

IL_0074:
	{
		// while (!done)
		bool L_33 = V_2;
		if (!L_33)
		{
			goto IL_0012;
		}
	}
	{
		// return table;
		Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* L_34 = V_0;
		return L_34;
	}
}
// System.Collections.ArrayList MiniJSON::parseArray(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* MiniJSON_parseArray_m82CFE877BAC4AAD629772733F5317E024054357A (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* V_0 = NULL;
	bool V_1 = false;
	int32_t V_2 = 0;
	bool V_3 = false;
	RuntimeObject* V_4 = NULL;
	{
		// ArrayList array = new ArrayList();
		ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* L_0 = (ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A*)il2cpp_codegen_object_new(ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		ArrayList__ctor_m07DC369002304B483B9FC41DBDAF4A25AC3C9F80(L_0, NULL);
		V_0 = L_0;
		// nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_1 = ___0_json;
		int32_t* L_2 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_3;
		L_3 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_1, L_2, NULL);
		// bool done = false;
		V_1 = (bool)0;
		goto IL_0057;
	}

IL_0012:
	{
		// int token = lookAhead(json, index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_4 = ___0_json;
		int32_t* L_5 = ___1_index;
		int32_t L_6 = *((int32_t*)L_5);
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_7;
		L_7 = MiniJSON_lookAhead_m2CD63C46AD1A3C35A8EF72D1ABB729F324E997C3(L_4, L_6, NULL);
		V_2 = L_7;
		// if (token == MiniJSON.TOKEN_NONE)
		int32_t L_8 = V_2;
		if (L_8)
		{
			goto IL_0020;
		}
	}
	{
		// return null;
		return (ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A*)NULL;
	}

IL_0020:
	{
		// else if (token == MiniJSON.TOKEN_COMMA)
		int32_t L_9 = V_2;
		if ((!(((uint32_t)L_9) == ((uint32_t)6))))
		{
			goto IL_002e;
		}
	}
	{
		// nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_10 = ___0_json;
		int32_t* L_11 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_12;
		L_12 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_10, L_11, NULL);
		goto IL_0057;
	}

IL_002e:
	{
		// else if (token == MiniJSON.TOKEN_SQUARED_CLOSE)
		int32_t L_13 = V_2;
		if ((!(((uint32_t)L_13) == ((uint32_t)4))))
		{
			goto IL_003c;
		}
	}
	{
		// nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_14 = ___0_json;
		int32_t* L_15 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_16;
		L_16 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_14, L_15, NULL);
		// break;
		goto IL_005a;
	}

IL_003c:
	{
		// bool success = true;
		V_3 = (bool)1;
		// object value = parseValue(json, ref index, ref success);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_17 = ___0_json;
		int32_t* L_18 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		RuntimeObject* L_19;
		L_19 = MiniJSON_parseValue_m6B209155B5F5B274451B6BCF1B3E4712CF2FA11E(L_17, L_18, (&V_3), NULL);
		V_4 = L_19;
		// if (!success)
		bool L_20 = V_3;
		if (L_20)
		{
			goto IL_004e;
		}
	}
	{
		// return null;
		return (ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A*)NULL;
	}

IL_004e:
	{
		// array.Add(value);
		ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* L_21 = V_0;
		RuntimeObject* L_22 = V_4;
		NullCheck(L_21);
		int32_t L_23;
		L_23 = VirtualFuncInvoker1< int32_t, RuntimeObject* >::Invoke(27 /* System.Int32 System.Collections.ArrayList::Add(System.Object) */, L_21, L_22);
	}

IL_0057:
	{
		// while (!done)
		bool L_24 = V_1;
		if (!L_24)
		{
			goto IL_0012;
		}
	}

IL_005a:
	{
		// return array;
		ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* L_25 = V_0;
		return L_25;
	}
}
// System.Object MiniJSON::parseValue(System.Char[],System.Int32&,System.Boolean&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* MiniJSON_parseValue_m6B209155B5F5B274451B6BCF1B3E4712CF2FA11E (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, bool* ___2_success, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Double_tE150EF3D1D43DEE85D533810AB4C742307EEDE5F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralA539038315AABE91ABF55F3F1AEE619EEBFC183A);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA88A2D964F57B37C63183F5236085B715F076B8);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	{
		// switch (lookAhead(json, index))
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_0 = ___0_json;
		int32_t* L_1 = ___1_index;
		int32_t L_2 = *((int32_t*)L_1);
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_3;
		L_3 = MiniJSON_lookAhead_m2CD63C46AD1A3C35A8EF72D1ABB729F324E997C3(L_0, L_2, NULL);
		V_0 = L_3;
		int32_t L_4 = V_0;
		switch (L_4)
		{
			case 0:
			{
				goto IL_00a0;
			}
			case 1:
			{
				goto IL_0056;
			}
			case 2:
			{
				goto IL_00a0;
			}
			case 3:
			{
				goto IL_005e;
			}
			case 4:
			{
				goto IL_00a0;
			}
			case 5:
			{
				goto IL_00a0;
			}
			case 6:
			{
				goto IL_00a0;
			}
			case 7:
			{
				goto IL_0041;
			}
			case 8:
			{
				goto IL_0049;
			}
			case 9:
			{
				goto IL_0066;
			}
			case 10:
			{
				goto IL_007e;
			}
			case 11:
			{
				goto IL_0096;
			}
		}
	}
	{
		goto IL_00a0;
	}

IL_0041:
	{
		// return parseString(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_5 = ___0_json;
		int32_t* L_6 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		String_t* L_7;
		L_7 = MiniJSON_parseString_mFFAA593ACB311A5EB76EF61FDAB5CB45BB5D11DC(L_5, L_6, NULL);
		return L_7;
	}

IL_0049:
	{
		// return parseNumber(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_8 = ___0_json;
		int32_t* L_9 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		double L_10;
		L_10 = MiniJSON_parseNumber_mC8633E070E7751E9BC8CE00D46D0883E3A14EA61(L_8, L_9, NULL);
		double L_11 = L_10;
		RuntimeObject* L_12 = Box(Double_tE150EF3D1D43DEE85D533810AB4C742307EEDE5F_il2cpp_TypeInfo_var, &L_11);
		return L_12;
	}

IL_0056:
	{
		// return parseObject(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_13 = ___0_json;
		int32_t* L_14 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* L_15;
		L_15 = MiniJSON_parseObject_m4EB6104BBF5A992C6F91F008ABC4ED995B83D21C(L_13, L_14, NULL);
		return L_15;
	}

IL_005e:
	{
		// return parseArray(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_16 = ___0_json;
		int32_t* L_17 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* L_18;
		L_18 = MiniJSON_parseArray_m82CFE877BAC4AAD629772733F5317E024054357A(L_16, L_17, NULL);
		return L_18;
	}

IL_0066:
	{
		// nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_19 = ___0_json;
		int32_t* L_20 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_21;
		L_21 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_19, L_20, NULL);
		// return Boolean.Parse("TRUE");
		il2cpp_codegen_runtime_class_init_inline(Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var);
		bool L_22;
		L_22 = Boolean_Parse_mC9B4406FE9DA904E2B7D75267CAD38E837D39A6D(_stringLiteralDA88A2D964F57B37C63183F5236085B715F076B8, NULL);
		bool L_23 = L_22;
		RuntimeObject* L_24 = Box(Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var, &L_23);
		return L_24;
	}

IL_007e:
	{
		// nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_25 = ___0_json;
		int32_t* L_26 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_27;
		L_27 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_25, L_26, NULL);
		// return Boolean.Parse("FALSE");
		il2cpp_codegen_runtime_class_init_inline(Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var);
		bool L_28;
		L_28 = Boolean_Parse_mC9B4406FE9DA904E2B7D75267CAD38E837D39A6D(_stringLiteralA539038315AABE91ABF55F3F1AEE619EEBFC183A, NULL);
		bool L_29 = L_28;
		RuntimeObject* L_30 = Box(Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var, &L_29);
		return L_30;
	}

IL_0096:
	{
		// nextToken(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_31 = ___0_json;
		int32_t* L_32 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_33;
		L_33 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_31, L_32, NULL);
		// return null;
		return NULL;
	}

IL_00a0:
	{
		// success = false;
		bool* L_34 = ___2_success;
		*((int8_t*)L_34) = (int8_t)0;
		// return null;
		return NULL;
	}
}
// System.String MiniJSON::parseString(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* MiniJSON_parseString_mFFAA593ACB311A5EB76EF61FDAB5CB45BB5D11DC (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Char_t521A6F19B456D956AF452D926C32709DC03D6B17_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral00B28FF06B788B9B67C6B259800F404F9F3761FD);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral053D8D6CEEBA9453C97D0EE5374DB863E6F77AD4);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral09B11B6CC411D8B9FFB75EAAE9A35B2AF248CE40);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral24313380B89749FA23D81C8CFE7ECADF5F282DF3);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral86BBAACC00198DBB3046818AD3FC2AA10AE48DE1);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralC62C64F00567C5368CAE37F4E64E1E82FF785677);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDB5B55A9B215F744DB82517864984D073F2E8F8C);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDE28F98354F48E7C0878BBA93033C6BDC68B27E2);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	Il2CppChar V_1 = 0x0;
	bool V_2 = false;
	int32_t V_3 = 0;
	CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* V_4 = NULL;
	uint32_t V_5 = 0;
	{
		// string s = "";
		V_0 = _stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709;
		// eatWhitespace(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_0 = ___0_json;
		int32_t* L_1 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		MiniJSON_eatWhitespace_mD6578C429FFFABCE23BFED2D14DD1279B2D2303A(L_0, L_1, NULL);
		// c = json[index++];
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_2 = ___0_json;
		int32_t* L_3 = ___1_index;
		int32_t* L_4 = ___1_index;
		int32_t L_5 = *((int32_t*)L_4);
		V_3 = L_5;
		int32_t L_6 = V_3;
		*((int32_t*)L_3) = (int32_t)((int32_t)il2cpp_codegen_add(L_6, 1));
		int32_t L_7 = V_3;
		NullCheck(L_2);
		int32_t L_8 = L_7;
		uint16_t L_9 = (uint16_t)(L_2)->GetAt(static_cast<il2cpp_array_size_t>(L_8));
		V_1 = L_9;
		// bool complete = false;
		V_2 = (bool)0;
		goto IL_0160;
	}

IL_0020:
	{
		// if (index == json.Length)
		int32_t* L_10 = ___1_index;
		int32_t L_11 = *((int32_t*)L_10);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_12 = ___0_json;
		NullCheck(L_12);
		if ((((int32_t)L_11) == ((int32_t)((int32_t)(((RuntimeArray*)L_12)->max_length)))))
		{
			goto IL_0166;
		}
	}
	{
		// c = json[index++];
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_13 = ___0_json;
		int32_t* L_14 = ___1_index;
		int32_t* L_15 = ___1_index;
		int32_t L_16 = *((int32_t*)L_15);
		V_3 = L_16;
		int32_t L_17 = V_3;
		*((int32_t*)L_14) = (int32_t)((int32_t)il2cpp_codegen_add(L_17, 1));
		int32_t L_18 = V_3;
		NullCheck(L_13);
		int32_t L_19 = L_18;
		uint16_t L_20 = (uint16_t)(L_13)->GetAt(static_cast<il2cpp_array_size_t>(L_19));
		V_1 = L_20;
		// if (c == '"')
		Il2CppChar L_21 = V_1;
		if ((!(((uint32_t)L_21) == ((uint32_t)((int32_t)34)))))
		{
			goto IL_0042;
		}
	}
	{
		// complete = true;
		V_2 = (bool)1;
		// break;
		goto IL_0166;
	}

IL_0042:
	{
		// else if (c == '\\')
		Il2CppChar L_22 = V_1;
		if ((!(((uint32_t)L_22) == ((uint32_t)((int32_t)92)))))
		{
			goto IL_0152;
		}
	}
	{
		// if (index == json.Length)
		int32_t* L_23 = ___1_index;
		int32_t L_24 = *((int32_t*)L_23);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_25 = ___0_json;
		NullCheck(L_25);
		if ((((int32_t)L_24) == ((int32_t)((int32_t)(((RuntimeArray*)L_25)->max_length)))))
		{
			goto IL_0166;
		}
	}
	{
		// c = json[index++];
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_26 = ___0_json;
		int32_t* L_27 = ___1_index;
		int32_t* L_28 = ___1_index;
		int32_t L_29 = *((int32_t*)L_28);
		V_3 = L_29;
		int32_t L_30 = V_3;
		*((int32_t*)L_27) = (int32_t)((int32_t)il2cpp_codegen_add(L_30, 1));
		int32_t L_31 = V_3;
		NullCheck(L_26);
		int32_t L_32 = L_31;
		uint16_t L_33 = (uint16_t)(L_26)->GetAt(static_cast<il2cpp_array_size_t>(L_32));
		V_1 = L_33;
		// if (c == '"')
		Il2CppChar L_34 = V_1;
		if ((!(((uint32_t)L_34) == ((uint32_t)((int32_t)34)))))
		{
			goto IL_0076;
		}
	}
	{
		// s += '"';
		String_t* L_35 = V_0;
		String_t* L_36;
		L_36 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_35, _stringLiteralC62C64F00567C5368CAE37F4E64E1E82FF785677, NULL);
		V_0 = L_36;
		goto IL_0160;
	}

IL_0076:
	{
		// else if (c == '\\')
		Il2CppChar L_37 = V_1;
		if ((!(((uint32_t)L_37) == ((uint32_t)((int32_t)92)))))
		{
			goto IL_008c;
		}
	}
	{
		// s += '\\';
		String_t* L_38 = V_0;
		String_t* L_39;
		L_39 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_38, _stringLiteral09B11B6CC411D8B9FFB75EAAE9A35B2AF248CE40, NULL);
		V_0 = L_39;
		goto IL_0160;
	}

IL_008c:
	{
		// else if (c == '/')
		Il2CppChar L_40 = V_1;
		if ((!(((uint32_t)L_40) == ((uint32_t)((int32_t)47)))))
		{
			goto IL_00a2;
		}
	}
	{
		// s += '/';
		String_t* L_41 = V_0;
		String_t* L_42;
		L_42 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_41, _stringLiteral86BBAACC00198DBB3046818AD3FC2AA10AE48DE1, NULL);
		V_0 = L_42;
		goto IL_0160;
	}

IL_00a2:
	{
		// else if (c == 'b')
		Il2CppChar L_43 = V_1;
		if ((!(((uint32_t)L_43) == ((uint32_t)((int32_t)98)))))
		{
			goto IL_00b8;
		}
	}
	{
		// s += '\b';
		String_t* L_44 = V_0;
		String_t* L_45;
		L_45 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_44, _stringLiteral053D8D6CEEBA9453C97D0EE5374DB863E6F77AD4, NULL);
		V_0 = L_45;
		goto IL_0160;
	}

IL_00b8:
	{
		// else if (c == 'f')
		Il2CppChar L_46 = V_1;
		if ((!(((uint32_t)L_46) == ((uint32_t)((int32_t)102)))))
		{
			goto IL_00ce;
		}
	}
	{
		// s += '\f';
		String_t* L_47 = V_0;
		String_t* L_48;
		L_48 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_47, _stringLiteralDE28F98354F48E7C0878BBA93033C6BDC68B27E2, NULL);
		V_0 = L_48;
		goto IL_0160;
	}

IL_00ce:
	{
		// else if (c == 'n')
		Il2CppChar L_49 = V_1;
		if ((!(((uint32_t)L_49) == ((uint32_t)((int32_t)110)))))
		{
			goto IL_00e1;
		}
	}
	{
		// s += '\n';
		String_t* L_50 = V_0;
		String_t* L_51;
		L_51 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_50, _stringLiteral00B28FF06B788B9B67C6B259800F404F9F3761FD, NULL);
		V_0 = L_51;
		goto IL_0160;
	}

IL_00e1:
	{
		// else if (c == 'r')
		Il2CppChar L_52 = V_1;
		if ((!(((uint32_t)L_52) == ((uint32_t)((int32_t)114)))))
		{
			goto IL_00f4;
		}
	}
	{
		// s += '\r';
		String_t* L_53 = V_0;
		String_t* L_54;
		L_54 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_53, _stringLiteralDB5B55A9B215F744DB82517864984D073F2E8F8C, NULL);
		V_0 = L_54;
		goto IL_0160;
	}

IL_00f4:
	{
		// else if (c == 't')
		Il2CppChar L_55 = V_1;
		if ((!(((uint32_t)L_55) == ((uint32_t)((int32_t)116)))))
		{
			goto IL_0107;
		}
	}
	{
		// s += '\t';
		String_t* L_56 = V_0;
		String_t* L_57;
		L_57 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_56, _stringLiteral24313380B89749FA23D81C8CFE7ECADF5F282DF3, NULL);
		V_0 = L_57;
		goto IL_0160;
	}

IL_0107:
	{
		// else if (c == 'u')
		Il2CppChar L_58 = V_1;
		if ((!(((uint32_t)L_58) == ((uint32_t)((int32_t)117)))))
		{
			goto IL_0160;
		}
	}
	{
		// int remainingLength = json.Length - index;
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_59 = ___0_json;
		NullCheck(L_59);
		int32_t* L_60 = ___1_index;
		int32_t L_61 = *((int32_t*)L_60);
		// if (remainingLength >= 4)
		if ((((int32_t)((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_59)->max_length)), L_61))) < ((int32_t)4)))
		{
			goto IL_0166;
		}
	}
	{
		// char[] unicodeCharArray = new char[4];
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_62 = (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB*)(CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB*)SZArrayNew(CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB_il2cpp_TypeInfo_var, (uint32_t)4);
		V_4 = L_62;
		// Array.Copy(json, index, unicodeCharArray, 0, 4);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_63 = ___0_json;
		int32_t* L_64 = ___1_index;
		int32_t L_65 = *((int32_t*)L_64);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_66 = V_4;
		Array_Copy_mB4904E17BD92E320613A3251C0205E0786B3BF41((RuntimeArray*)L_63, L_65, (RuntimeArray*)L_66, 0, 4, NULL);
		// uint codePoint = UInt32.Parse(new string(unicodeCharArray), System.Globalization.NumberStyles.HexNumber);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_67 = V_4;
		String_t* L_68;
		L_68 = String_CreateString_mFBC28D2E3EB87D497F7E702E4FFAD65F635E44DF(NULL, L_67, NULL);
		uint32_t L_69;
		L_69 = UInt32_Parse_m5B91BE3C4654E3FA80F55AED00460D7234961E9E(L_68, ((int32_t)515), NULL);
		V_5 = L_69;
		// s += Char.ConvertFromUtf32((int)codePoint);
		String_t* L_70 = V_0;
		uint32_t L_71 = V_5;
		il2cpp_codegen_runtime_class_init_inline(Char_t521A6F19B456D956AF452D926C32709DC03D6B17_il2cpp_TypeInfo_var);
		String_t* L_72;
		L_72 = Char_ConvertFromUtf32_m6AC7B7F6371FC03CDD4C42CEC8B9ACE751CE3BC8(L_71, NULL);
		String_t* L_73;
		L_73 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_70, L_72, NULL);
		V_0 = L_73;
		// index += 4;
		int32_t* L_74 = ___1_index;
		int32_t* L_75 = ___1_index;
		int32_t L_76 = *((int32_t*)L_75);
		*((int32_t*)L_74) = (int32_t)((int32_t)il2cpp_codegen_add(L_76, 4));
		goto IL_0160;
	}

IL_0152:
	{
		// s += c;
		String_t* L_77 = V_0;
		String_t* L_78;
		L_78 = Char_ToString_m2A308731F9577C06AF3C0901234E2EAC8327410C((&V_1), NULL);
		String_t* L_79;
		L_79 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(L_77, L_78, NULL);
		V_0 = L_79;
	}

IL_0160:
	{
		// while (!complete)
		bool L_80 = V_2;
		if (!L_80)
		{
			goto IL_0020;
		}
	}

IL_0166:
	{
		// if (!complete)
		bool L_81 = V_2;
		if (L_81)
		{
			goto IL_016b;
		}
	}
	{
		// return null;
		return (String_t*)NULL;
	}

IL_016b:
	{
		// return s;
		String_t* L_82 = V_0;
		return L_82;
	}
}
// System.Double MiniJSON::parseNumber(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR double MiniJSON_parseNumber_mC8633E070E7751E9BC8CE00D46D0883E3A14EA61 (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	int32_t V_1 = 0;
	CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* V_2 = NULL;
	{
		// eatWhitespace(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_0 = ___0_json;
		int32_t* L_1 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		MiniJSON_eatWhitespace_mD6578C429FFFABCE23BFED2D14DD1279B2D2303A(L_0, L_1, NULL);
		// int lastIndex = getLastIndexOfNumber(json, index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_2 = ___0_json;
		int32_t* L_3 = ___1_index;
		int32_t L_4 = *((int32_t*)L_3);
		int32_t L_5;
		L_5 = MiniJSON_getLastIndexOfNumber_mC698C4013038DC3B280F7CCEF78CA899908D97A9(L_2, L_4, NULL);
		V_0 = L_5;
		// int charLength = (lastIndex - index) + 1;
		int32_t L_6 = V_0;
		int32_t* L_7 = ___1_index;
		int32_t L_8 = *((int32_t*)L_7);
		V_1 = ((int32_t)il2cpp_codegen_add(((int32_t)il2cpp_codegen_subtract(L_6, L_8)), 1));
		// char[] numberCharArray = new char[charLength];
		int32_t L_9 = V_1;
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_10 = (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB*)(CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB*)SZArrayNew(CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB_il2cpp_TypeInfo_var, (uint32_t)L_9);
		V_2 = L_10;
		// Array.Copy(json, index, numberCharArray, 0, charLength);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_11 = ___0_json;
		int32_t* L_12 = ___1_index;
		int32_t L_13 = *((int32_t*)L_12);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_14 = V_2;
		int32_t L_15 = V_1;
		Array_Copy_mB4904E17BD92E320613A3251C0205E0786B3BF41((RuntimeArray*)L_11, L_13, (RuntimeArray*)L_14, 0, L_15, NULL);
		// index = lastIndex + 1;
		int32_t* L_16 = ___1_index;
		int32_t L_17 = V_0;
		*((int32_t*)L_16) = (int32_t)((int32_t)il2cpp_codegen_add(L_17, 1));
		// return Double.Parse(new string(numberCharArray)); // , CultureInfo.InvariantCulture);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_18 = V_2;
		String_t* L_19;
		L_19 = String_CreateString_mFBC28D2E3EB87D497F7E702E4FFAD65F635E44DF(NULL, L_18, NULL);
		double L_20;
		L_20 = Double_Parse_mEBC644CE0C86A405283EC4839F872EF9E556670A(L_19, NULL);
		return L_20;
	}
}
// System.Int32 MiniJSON::getLastIndexOfNumber(System.Char[],System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t MiniJSON_getLastIndexOfNumber_mC698C4013038DC3B280F7CCEF78CA899908D97A9 (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t ___1_index, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral750483B1B79D7876103D12AD91A365DD2A867A80);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	{
		// for (lastIndex = index; lastIndex < json.Length; lastIndex++)
		int32_t L_0 = ___1_index;
		V_0 = L_0;
		goto IL_0018;
	}

IL_0004:
	{
		// if ("0123456789+-.eE".IndexOf(json[lastIndex]) == -1)
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_1 = ___0_json;
		int32_t L_2 = V_0;
		NullCheck(L_1);
		int32_t L_3 = L_2;
		uint16_t L_4 = (uint16_t)(L_1)->GetAt(static_cast<il2cpp_array_size_t>(L_3));
		NullCheck(_stringLiteral750483B1B79D7876103D12AD91A365DD2A867A80);
		int32_t L_5;
		L_5 = String_IndexOf_mE21E78F35EF4A7768E385A72814C88D22B689966(_stringLiteral750483B1B79D7876103D12AD91A365DD2A867A80, L_4, NULL);
		if ((((int32_t)L_5) == ((int32_t)(-1))))
		{
			goto IL_001e;
		}
	}
	{
		// for (lastIndex = index; lastIndex < json.Length; lastIndex++)
		int32_t L_6 = V_0;
		V_0 = ((int32_t)il2cpp_codegen_add(L_6, 1));
	}

IL_0018:
	{
		// for (lastIndex = index; lastIndex < json.Length; lastIndex++)
		int32_t L_7 = V_0;
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_8 = ___0_json;
		NullCheck(L_8);
		if ((((int32_t)L_7) < ((int32_t)((int32_t)(((RuntimeArray*)L_8)->max_length)))))
		{
			goto IL_0004;
		}
	}

IL_001e:
	{
		// return lastIndex - 1;
		int32_t L_9 = V_0;
		return ((int32_t)il2cpp_codegen_subtract(L_9, 1));
	}
}
// System.Void MiniJSON::eatWhitespace(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void MiniJSON_eatWhitespace_mD6578C429FFFABCE23BFED2D14DD1279B2D2303A (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralEBC658B067B5C785A3F0BB67D73755F6FEE7F70C);
		s_Il2CppMethodInitialized = true;
	}
	{
		goto IL_0019;
	}

IL_0002:
	{
		// if (" \t\n\r".IndexOf(json[index]) == -1)
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_0 = ___0_json;
		int32_t* L_1 = ___1_index;
		int32_t L_2 = *((int32_t*)L_1);
		NullCheck(L_0);
		int32_t L_3 = L_2;
		uint16_t L_4 = (uint16_t)(L_0)->GetAt(static_cast<il2cpp_array_size_t>(L_3));
		NullCheck(_stringLiteralEBC658B067B5C785A3F0BB67D73755F6FEE7F70C);
		int32_t L_5;
		L_5 = String_IndexOf_mE21E78F35EF4A7768E385A72814C88D22B689966(_stringLiteralEBC658B067B5C785A3F0BB67D73755F6FEE7F70C, L_4, NULL);
		if ((((int32_t)L_5) == ((int32_t)(-1))))
		{
			goto IL_0020;
		}
	}
	{
		// for (; index < json.Length; index++)
		int32_t* L_6 = ___1_index;
		int32_t* L_7 = ___1_index;
		int32_t L_8 = *((int32_t*)L_7);
		*((int32_t*)L_6) = (int32_t)((int32_t)il2cpp_codegen_add(L_8, 1));
	}

IL_0019:
	{
		// for (; index < json.Length; index++)
		int32_t* L_9 = ___1_index;
		int32_t L_10 = *((int32_t*)L_9);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_11 = ___0_json;
		NullCheck(L_11);
		if ((((int32_t)L_10) < ((int32_t)((int32_t)(((RuntimeArray*)L_11)->max_length)))))
		{
			goto IL_0002;
		}
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Int32 MiniJSON::lookAhead(System.Char[],System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t MiniJSON_lookAhead_m2CD63C46AD1A3C35A8EF72D1ABB729F324E997C3 (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t ___1_index, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	{
		// int saveIndex = index;
		int32_t L_0 = ___1_index;
		V_0 = L_0;
		// return nextToken(json, ref saveIndex);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_1 = ___0_json;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		int32_t L_2;
		L_2 = MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990(L_1, (&V_0), NULL);
		return L_2;
	}
}
// System.Int32 MiniJSON::nextToken(System.Char[],System.Int32&)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t MiniJSON_nextToken_mE75F3B57F1EB3E7F991145ACDE957A9922223990 (CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* ___0_json, int32_t* ___1_index, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Il2CppChar V_0 = 0x0;
	int32_t V_1 = 0;
	{
		// eatWhitespace(json, ref index);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_0 = ___0_json;
		int32_t* L_1 = ___1_index;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		MiniJSON_eatWhitespace_mD6578C429FFFABCE23BFED2D14DD1279B2D2303A(L_0, L_1, NULL);
		// if (index == json.Length)
		int32_t* L_2 = ___1_index;
		int32_t L_3 = *((int32_t*)L_2);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_4 = ___0_json;
		NullCheck(L_4);
		if ((!(((uint32_t)L_3) == ((uint32_t)((int32_t)(((RuntimeArray*)L_4)->max_length))))))
		{
			goto IL_0010;
		}
	}
	{
		// return MiniJSON.TOKEN_NONE;
		return 0;
	}

IL_0010:
	{
		// char c = json[index];
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_5 = ___0_json;
		int32_t* L_6 = ___1_index;
		int32_t L_7 = *((int32_t*)L_6);
		NullCheck(L_5);
		int32_t L_8 = L_7;
		uint16_t L_9 = (uint16_t)(L_5)->GetAt(static_cast<il2cpp_array_size_t>(L_8));
		V_0 = L_9;
		// index++;
		int32_t* L_10 = ___1_index;
		int32_t* L_11 = ___1_index;
		int32_t L_12 = *((int32_t*)L_11);
		*((int32_t*)L_10) = (int32_t)((int32_t)il2cpp_codegen_add(L_12, 1));
		Il2CppChar L_13 = V_0;
		if ((!(((uint32_t)L_13) <= ((uint32_t)((int32_t)91)))))
		{
			goto IL_0094;
		}
	}
	{
		Il2CppChar L_14 = V_0;
		switch (((int32_t)il2cpp_codegen_subtract((int32_t)L_14, ((int32_t)34))))
		{
			case 0:
			{
				goto IL_00af;
			}
			case 1:
			{
				goto IL_00b5;
			}
			case 2:
			{
				goto IL_00b5;
			}
			case 3:
			{
				goto IL_00b5;
			}
			case 4:
			{
				goto IL_00b5;
			}
			case 5:
			{
				goto IL_00b5;
			}
			case 6:
			{
				goto IL_00b5;
			}
			case 7:
			{
				goto IL_00b5;
			}
			case 8:
			{
				goto IL_00b5;
			}
			case 9:
			{
				goto IL_00b5;
			}
			case 10:
			{
				goto IL_00ad;
			}
			case 11:
			{
				goto IL_00b1;
			}
			case 12:
			{
				goto IL_00b5;
			}
			case 13:
			{
				goto IL_00b5;
			}
			case 14:
			{
				goto IL_00b1;
			}
			case 15:
			{
				goto IL_00b1;
			}
			case 16:
			{
				goto IL_00b1;
			}
			case 17:
			{
				goto IL_00b1;
			}
			case 18:
			{
				goto IL_00b1;
			}
			case 19:
			{
				goto IL_00b1;
			}
			case 20:
			{
				goto IL_00b1;
			}
			case 21:
			{
				goto IL_00b1;
			}
			case 22:
			{
				goto IL_00b1;
			}
			case 23:
			{
				goto IL_00b1;
			}
			case 24:
			{
				goto IL_00b3;
			}
		}
	}
	{
		Il2CppChar L_15 = V_0;
		if ((((int32_t)L_15) == ((int32_t)((int32_t)91))))
		{
			goto IL_00a9;
		}
	}
	{
		goto IL_00b5;
	}

IL_0094:
	{
		Il2CppChar L_16 = V_0;
		if ((((int32_t)L_16) == ((int32_t)((int32_t)93))))
		{
			goto IL_00ab;
		}
	}
	{
		Il2CppChar L_17 = V_0;
		if ((((int32_t)L_17) == ((int32_t)((int32_t)123))))
		{
			goto IL_00a5;
		}
	}
	{
		Il2CppChar L_18 = V_0;
		if ((((int32_t)L_18) == ((int32_t)((int32_t)125))))
		{
			goto IL_00a7;
		}
	}
	{
		goto IL_00b5;
	}

IL_00a5:
	{
		// return MiniJSON.TOKEN_CURLY_OPEN;
		return 1;
	}

IL_00a7:
	{
		// return MiniJSON.TOKEN_CURLY_CLOSE;
		return 2;
	}

IL_00a9:
	{
		// return MiniJSON.TOKEN_SQUARED_OPEN;
		return 3;
	}

IL_00ab:
	{
		// return MiniJSON.TOKEN_SQUARED_CLOSE;
		return 4;
	}

IL_00ad:
	{
		// return MiniJSON.TOKEN_COMMA;
		return 6;
	}

IL_00af:
	{
		// return MiniJSON.TOKEN_STRING;
		return 7;
	}

IL_00b1:
	{
		// return MiniJSON.TOKEN_NUMBER;
		return 8;
	}

IL_00b3:
	{
		// return MiniJSON.TOKEN_COLON;
		return 5;
	}

IL_00b5:
	{
		// index--;
		int32_t* L_19 = ___1_index;
		int32_t* L_20 = ___1_index;
		int32_t L_21 = *((int32_t*)L_20);
		*((int32_t*)L_19) = (int32_t)((int32_t)il2cpp_codegen_subtract(L_21, 1));
		// int remainingLength = json.Length - index;
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_22 = ___0_json;
		NullCheck(L_22);
		int32_t* L_23 = ___1_index;
		int32_t L_24 = *((int32_t*)L_23);
		V_1 = ((int32_t)il2cpp_codegen_subtract(((int32_t)(((RuntimeArray*)L_22)->max_length)), L_24));
		// if (remainingLength >= 5)
		int32_t L_25 = V_1;
		if ((((int32_t)L_25) < ((int32_t)5)))
		{
			goto IL_00ff;
		}
	}
	{
		// if (json[index] == 'f' &&
		//     json[index + 1] == 'a' &&
		//     json[index + 2] == 'l' &&
		//     json[index + 3] == 's' &&
		//     json[index + 4] == 'e')
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_26 = ___0_json;
		int32_t* L_27 = ___1_index;
		int32_t L_28 = *((int32_t*)L_27);
		NullCheck(L_26);
		int32_t L_29 = L_28;
		uint16_t L_30 = (uint16_t)(L_26)->GetAt(static_cast<il2cpp_array_size_t>(L_29));
		if ((!(((uint32_t)L_30) == ((uint32_t)((int32_t)102)))))
		{
			goto IL_00ff;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_31 = ___0_json;
		int32_t* L_32 = ___1_index;
		int32_t L_33 = *((int32_t*)L_32);
		NullCheck(L_31);
		int32_t L_34 = ((int32_t)il2cpp_codegen_add(L_33, 1));
		uint16_t L_35 = (uint16_t)(L_31)->GetAt(static_cast<il2cpp_array_size_t>(L_34));
		if ((!(((uint32_t)L_35) == ((uint32_t)((int32_t)97)))))
		{
			goto IL_00ff;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_36 = ___0_json;
		int32_t* L_37 = ___1_index;
		int32_t L_38 = *((int32_t*)L_37);
		NullCheck(L_36);
		int32_t L_39 = ((int32_t)il2cpp_codegen_add(L_38, 2));
		uint16_t L_40 = (uint16_t)(L_36)->GetAt(static_cast<il2cpp_array_size_t>(L_39));
		if ((!(((uint32_t)L_40) == ((uint32_t)((int32_t)108)))))
		{
			goto IL_00ff;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_41 = ___0_json;
		int32_t* L_42 = ___1_index;
		int32_t L_43 = *((int32_t*)L_42);
		NullCheck(L_41);
		int32_t L_44 = ((int32_t)il2cpp_codegen_add(L_43, 3));
		uint16_t L_45 = (uint16_t)(L_41)->GetAt(static_cast<il2cpp_array_size_t>(L_44));
		if ((!(((uint32_t)L_45) == ((uint32_t)((int32_t)115)))))
		{
			goto IL_00ff;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_46 = ___0_json;
		int32_t* L_47 = ___1_index;
		int32_t L_48 = *((int32_t*)L_47);
		NullCheck(L_46);
		int32_t L_49 = ((int32_t)il2cpp_codegen_add(L_48, 4));
		uint16_t L_50 = (uint16_t)(L_46)->GetAt(static_cast<il2cpp_array_size_t>(L_49));
		if ((!(((uint32_t)L_50) == ((uint32_t)((int32_t)101)))))
		{
			goto IL_00ff;
		}
	}
	{
		// index += 5;
		int32_t* L_51 = ___1_index;
		int32_t* L_52 = ___1_index;
		int32_t L_53 = *((int32_t*)L_52);
		*((int32_t*)L_51) = (int32_t)((int32_t)il2cpp_codegen_add(L_53, 5));
		// return MiniJSON.TOKEN_FALSE;
		return ((int32_t)10);
	}

IL_00ff:
	{
		// if (remainingLength >= 4)
		int32_t L_54 = V_1;
		if ((((int32_t)L_54) < ((int32_t)4)))
		{
			goto IL_0132;
		}
	}
	{
		// if (json[index] == 't' &&
		//     json[index + 1] == 'r' &&
		//     json[index + 2] == 'u' &&
		//     json[index + 3] == 'e')
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_55 = ___0_json;
		int32_t* L_56 = ___1_index;
		int32_t L_57 = *((int32_t*)L_56);
		NullCheck(L_55);
		int32_t L_58 = L_57;
		uint16_t L_59 = (uint16_t)(L_55)->GetAt(static_cast<il2cpp_array_size_t>(L_58));
		if ((!(((uint32_t)L_59) == ((uint32_t)((int32_t)116)))))
		{
			goto IL_0132;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_60 = ___0_json;
		int32_t* L_61 = ___1_index;
		int32_t L_62 = *((int32_t*)L_61);
		NullCheck(L_60);
		int32_t L_63 = ((int32_t)il2cpp_codegen_add(L_62, 1));
		uint16_t L_64 = (uint16_t)(L_60)->GetAt(static_cast<il2cpp_array_size_t>(L_63));
		if ((!(((uint32_t)L_64) == ((uint32_t)((int32_t)114)))))
		{
			goto IL_0132;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_65 = ___0_json;
		int32_t* L_66 = ___1_index;
		int32_t L_67 = *((int32_t*)L_66);
		NullCheck(L_65);
		int32_t L_68 = ((int32_t)il2cpp_codegen_add(L_67, 2));
		uint16_t L_69 = (uint16_t)(L_65)->GetAt(static_cast<il2cpp_array_size_t>(L_68));
		if ((!(((uint32_t)L_69) == ((uint32_t)((int32_t)117)))))
		{
			goto IL_0132;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_70 = ___0_json;
		int32_t* L_71 = ___1_index;
		int32_t L_72 = *((int32_t*)L_71);
		NullCheck(L_70);
		int32_t L_73 = ((int32_t)il2cpp_codegen_add(L_72, 3));
		uint16_t L_74 = (uint16_t)(L_70)->GetAt(static_cast<il2cpp_array_size_t>(L_73));
		if ((!(((uint32_t)L_74) == ((uint32_t)((int32_t)101)))))
		{
			goto IL_0132;
		}
	}
	{
		// index += 4;
		int32_t* L_75 = ___1_index;
		int32_t* L_76 = ___1_index;
		int32_t L_77 = *((int32_t*)L_76);
		*((int32_t*)L_75) = (int32_t)((int32_t)il2cpp_codegen_add(L_77, 4));
		// return MiniJSON.TOKEN_TRUE;
		return ((int32_t)9);
	}

IL_0132:
	{
		// if (remainingLength >= 4)
		int32_t L_78 = V_1;
		if ((((int32_t)L_78) < ((int32_t)4)))
		{
			goto IL_0165;
		}
	}
	{
		// if (json[index] == 'n' &&
		//     json[index + 1] == 'u' &&
		//     json[index + 2] == 'l' &&
		//     json[index + 3] == 'l')
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_79 = ___0_json;
		int32_t* L_80 = ___1_index;
		int32_t L_81 = *((int32_t*)L_80);
		NullCheck(L_79);
		int32_t L_82 = L_81;
		uint16_t L_83 = (uint16_t)(L_79)->GetAt(static_cast<il2cpp_array_size_t>(L_82));
		if ((!(((uint32_t)L_83) == ((uint32_t)((int32_t)110)))))
		{
			goto IL_0165;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_84 = ___0_json;
		int32_t* L_85 = ___1_index;
		int32_t L_86 = *((int32_t*)L_85);
		NullCheck(L_84);
		int32_t L_87 = ((int32_t)il2cpp_codegen_add(L_86, 1));
		uint16_t L_88 = (uint16_t)(L_84)->GetAt(static_cast<il2cpp_array_size_t>(L_87));
		if ((!(((uint32_t)L_88) == ((uint32_t)((int32_t)117)))))
		{
			goto IL_0165;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_89 = ___0_json;
		int32_t* L_90 = ___1_index;
		int32_t L_91 = *((int32_t*)L_90);
		NullCheck(L_89);
		int32_t L_92 = ((int32_t)il2cpp_codegen_add(L_91, 2));
		uint16_t L_93 = (uint16_t)(L_89)->GetAt(static_cast<il2cpp_array_size_t>(L_92));
		if ((!(((uint32_t)L_93) == ((uint32_t)((int32_t)108)))))
		{
			goto IL_0165;
		}
	}
	{
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_94 = ___0_json;
		int32_t* L_95 = ___1_index;
		int32_t L_96 = *((int32_t*)L_95);
		NullCheck(L_94);
		int32_t L_97 = ((int32_t)il2cpp_codegen_add(L_96, 3));
		uint16_t L_98 = (uint16_t)(L_94)->GetAt(static_cast<il2cpp_array_size_t>(L_97));
		if ((!(((uint32_t)L_98) == ((uint32_t)((int32_t)108)))))
		{
			goto IL_0165;
		}
	}
	{
		// index += 4;
		int32_t* L_99 = ___1_index;
		int32_t* L_100 = ___1_index;
		int32_t L_101 = *((int32_t*)L_100);
		*((int32_t*)L_99) = (int32_t)((int32_t)il2cpp_codegen_add(L_101, 4));
		// return MiniJSON.TOKEN_NULL;
		return ((int32_t)11);
	}

IL_0165:
	{
		// return MiniJSON.TOKEN_NONE;
		return 0;
	}
}
// System.Boolean MiniJSON::serializeObjectOrArray(System.Object,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_serializeObjectOrArray_mF3BD8DC48C737C0FC1D93B540246D203DC575657 (RuntimeObject* ___0_objectOrArray, StringBuilder_t* ___1_builder, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (objectOrArray is Hashtable)
		RuntimeObject* L_0 = ___0_objectOrArray;
		if (!((Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)IsInstClass((RuntimeObject*)L_0, Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var)))
		{
			goto IL_0015;
		}
	}
	{
		// return serializeObject((Hashtable)objectOrArray, builder);
		RuntimeObject* L_1 = ___0_objectOrArray;
		StringBuilder_t* L_2 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		bool L_3;
		L_3 = MiniJSON_serializeObject_m0242063BB8D2B4C1A0EB136B4EC3E1F7A37948B6(((Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)CastclassClass((RuntimeObject*)L_1, Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var)), L_2, NULL);
		return L_3;
	}

IL_0015:
	{
		// else if (objectOrArray is ArrayList)
		RuntimeObject* L_4 = ___0_objectOrArray;
		if (!((ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A*)IsInstClass((RuntimeObject*)L_4, ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var)))
		{
			goto IL_002a;
		}
	}
	{
		// return serializeArray((ArrayList)objectOrArray, builder);
		RuntimeObject* L_5 = ___0_objectOrArray;
		StringBuilder_t* L_6 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		bool L_7;
		L_7 = MiniJSON_serializeArray_m50C5CB05BA9F556F76F9E3275750DADC8F13D5B2(((ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A*)CastclassClass((RuntimeObject*)L_5, ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var)), L_6, NULL);
		return L_7;
	}

IL_002a:
	{
		// return false;
		return (bool)0;
	}
}
// System.Boolean MiniJSON::serializeObject(System.Collections.Hashtable,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_serializeObject_m0242063BB8D2B4C1A0EB136B4EC3E1F7A37948B6 (Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* ___0_anObject, StringBuilder_t* ___1_builder, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IDictionaryEnumerator_tE129D608FCDB7207E0F0ECE33473CC950A83AD16_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IEnumerator_t7B609C2FFA6EB5167D9C62A0C32A21DE2F666DAA_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0C3C6829C3CCF8020C6AC45B87963ADC095CD44A);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral4D8D9C94AC5DA5FCED2EC8A64E10E714A2515C30);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral758733BDBED83CBFF4F635AC26CA92AAE477F75D);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral876C4B39B6E4D0187090400768899C71D99DE90D);
		s_Il2CppMethodInitialized = true;
	}
	RuntimeObject* V_0 = NULL;
	bool V_1 = false;
	String_t* V_2 = NULL;
	RuntimeObject* G_B3_0 = NULL;
	RuntimeObject* G_B2_0 = NULL;
	{
		// builder.Append("{");
		StringBuilder_t* L_0 = ___1_builder;
		NullCheck(L_0);
		StringBuilder_t* L_1;
		L_1 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_0, _stringLiteral0C3C6829C3CCF8020C6AC45B87963ADC095CD44A, NULL);
		// IDictionaryEnumerator e = anObject.GetEnumerator();
		Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* L_2 = ___0_anObject;
		NullCheck(L_2);
		RuntimeObject* L_3;
		L_3 = VirtualFuncInvoker0< RuntimeObject* >::Invoke(23 /* System.Collections.IDictionaryEnumerator System.Collections.Hashtable::GetEnumerator() */, L_2);
		V_0 = L_3;
		// bool first = true;
		V_1 = (bool)1;
		goto IL_0057;
	}

IL_0017:
	{
		// string key = e.Key.ToString();
		RuntimeObject* L_4 = V_0;
		NullCheck(L_4);
		RuntimeObject* L_5;
		L_5 = InterfaceFuncInvoker0< RuntimeObject* >::Invoke(0 /* System.Object System.Collections.IDictionaryEnumerator::get_Key() */, IDictionaryEnumerator_tE129D608FCDB7207E0F0ECE33473CC950A83AD16_il2cpp_TypeInfo_var, L_4);
		NullCheck(L_5);
		String_t* L_6;
		L_6 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_5);
		V_2 = L_6;
		// object value = e.Value;
		RuntimeObject* L_7 = V_0;
		NullCheck(L_7);
		RuntimeObject* L_8;
		L_8 = InterfaceFuncInvoker0< RuntimeObject* >::Invoke(1 /* System.Object System.Collections.IDictionaryEnumerator::get_Value() */, IDictionaryEnumerator_tE129D608FCDB7207E0F0ECE33473CC950A83AD16_il2cpp_TypeInfo_var, L_7);
		// if (!first)
		bool L_9 = V_1;
		G_B2_0 = L_8;
		if (L_9)
		{
			G_B3_0 = L_8;
			goto IL_0038;
		}
	}
	{
		// builder.Append(", ");
		StringBuilder_t* L_10 = ___1_builder;
		NullCheck(L_10);
		StringBuilder_t* L_11;
		L_11 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_10, _stringLiteral758733BDBED83CBFF4F635AC26CA92AAE477F75D, NULL);
		G_B3_0 = G_B2_0;
	}

IL_0038:
	{
		// serializeString(key, builder);
		String_t* L_12 = V_2;
		StringBuilder_t* L_13 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		MiniJSON_serializeString_mF0CB910410E52BC1C0F0E1A30AA0FBF1A1C48B96(L_12, L_13, NULL);
		// builder.Append(":");
		StringBuilder_t* L_14 = ___1_builder;
		NullCheck(L_14);
		StringBuilder_t* L_15;
		L_15 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_14, _stringLiteral876C4B39B6E4D0187090400768899C71D99DE90D, NULL);
		// if (!serializeValue(value, builder))
		StringBuilder_t* L_16 = ___1_builder;
		bool L_17;
		L_17 = MiniJSON_serializeValue_mA5FACD9C89912FCDAEF66E9919E04A67235C1D5A(G_B3_0, L_16, NULL);
		if (L_17)
		{
			goto IL_0055;
		}
	}
	{
		// return false;
		return (bool)0;
	}

IL_0055:
	{
		// first = false;
		V_1 = (bool)0;
	}

IL_0057:
	{
		// while (e.MoveNext())
		RuntimeObject* L_18 = V_0;
		NullCheck(L_18);
		bool L_19;
		L_19 = InterfaceFuncInvoker0< bool >::Invoke(0 /* System.Boolean System.Collections.IEnumerator::MoveNext() */, IEnumerator_t7B609C2FFA6EB5167D9C62A0C32A21DE2F666DAA_il2cpp_TypeInfo_var, L_18);
		if (L_19)
		{
			goto IL_0017;
		}
	}
	{
		// builder.Append("}");
		StringBuilder_t* L_20 = ___1_builder;
		NullCheck(L_20);
		StringBuilder_t* L_21;
		L_21 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_20, _stringLiteral4D8D9C94AC5DA5FCED2EC8A64E10E714A2515C30, NULL);
		// return true;
		return (bool)1;
	}
}
// System.Boolean MiniJSON::serializeDictionary(System.Collections.Generic.Dictionary`2<System.String,System.String>,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_serializeDictionary_m99EA287B2265F8C5B00FE3FB0EB294A56BA374DB (Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83* ___0_dict, StringBuilder_t* ___1_builder, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Dictionary_2_GetEnumerator_m46EC45F42CA2279D83568CD3F216AAABA8E749F6_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Enumerator_Dispose_m068DDFF5CAFBB15C8A0602DEADA7F10C5BB7ADCD_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Enumerator_MoveNext_mA93491D9B55547D066053F3BC0A69C635F877438_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Enumerator_get_Current_m49070E88C2E34AB46E6292A3FB1C227576B8506E_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&KeyValuePair_2_get_Key_m654BCCAE2F20CB11D8E8C2D2C886A0C8A13EB1C4_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&KeyValuePair_2_get_Value_m7345512A32CB4DCAA0643050B18DC8DCD71B927A_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0C3C6829C3CCF8020C6AC45B87963ADC095CD44A);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral4D8D9C94AC5DA5FCED2EC8A64E10E714A2515C30);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral758733BDBED83CBFF4F635AC26CA92AAE477F75D);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral876C4B39B6E4D0187090400768899C71D99DE90D);
		s_Il2CppMethodInitialized = true;
	}
	bool V_0 = false;
	Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562 V_1;
	memset((&V_1), 0, sizeof(V_1));
	KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A V_2;
	memset((&V_2), 0, sizeof(V_2));
	{
		// builder.Append("{");
		StringBuilder_t* L_0 = ___1_builder;
		NullCheck(L_0);
		StringBuilder_t* L_1;
		L_1 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_0, _stringLiteral0C3C6829C3CCF8020C6AC45B87963ADC095CD44A, NULL);
		// bool first = true;
		V_0 = (bool)1;
		// foreach (var kv in dict)
		Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83* L_2 = ___0_dict;
		NullCheck(L_2);
		Enumerator_t173E7BE1F35CA448C7E0EE77345C9E0EC0206562 L_3;
		L_3 = Dictionary_2_GetEnumerator_m46EC45F42CA2279D83568CD3F216AAABA8E749F6(L_2, Dictionary_2_GetEnumerator_m46EC45F42CA2279D83568CD3F216AAABA8E749F6_RuntimeMethod_var);
		V_1 = L_3;
	}
	{
		auto __finallyBlock = il2cpp::utils::Finally([&]
		{

FINALLY_0061:
			{// begin finally (depth: 1)
				Enumerator_Dispose_m068DDFF5CAFBB15C8A0602DEADA7F10C5BB7ADCD((&V_1), Enumerator_Dispose_m068DDFF5CAFBB15C8A0602DEADA7F10C5BB7ADCD_RuntimeMethod_var);
				return;
			}// end finally (depth: 1)
		});
		try
		{// begin try (depth: 1)
			{
				goto IL_0056_1;
			}

IL_0017_1:
			{
				// foreach (var kv in dict)
				KeyValuePair_2_t47AB280304B50F542FD7E14F25DB2C374AEDD80A L_4;
				L_4 = Enumerator_get_Current_m49070E88C2E34AB46E6292A3FB1C227576B8506E_inline((&V_1), Enumerator_get_Current_m49070E88C2E34AB46E6292A3FB1C227576B8506E_RuntimeMethod_var);
				V_2 = L_4;
				// if (!first)
				bool L_5 = V_0;
				if (L_5)
				{
					goto IL_002e_1;
				}
			}
			{
				// builder.Append(", ");
				StringBuilder_t* L_6 = ___1_builder;
				NullCheck(L_6);
				StringBuilder_t* L_7;
				L_7 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_6, _stringLiteral758733BDBED83CBFF4F635AC26CA92AAE477F75D, NULL);
			}

IL_002e_1:
			{
				// serializeString(kv.Key, builder);
				String_t* L_8;
				L_8 = KeyValuePair_2_get_Key_m654BCCAE2F20CB11D8E8C2D2C886A0C8A13EB1C4_inline((&V_2), KeyValuePair_2_get_Key_m654BCCAE2F20CB11D8E8C2D2C886A0C8A13EB1C4_RuntimeMethod_var);
				StringBuilder_t* L_9 = ___1_builder;
				il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
				MiniJSON_serializeString_mF0CB910410E52BC1C0F0E1A30AA0FBF1A1C48B96(L_8, L_9, NULL);
				// builder.Append(":");
				StringBuilder_t* L_10 = ___1_builder;
				NullCheck(L_10);
				StringBuilder_t* L_11;
				L_11 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_10, _stringLiteral876C4B39B6E4D0187090400768899C71D99DE90D, NULL);
				// serializeString(kv.Value, builder);
				String_t* L_12;
				L_12 = KeyValuePair_2_get_Value_m7345512A32CB4DCAA0643050B18DC8DCD71B927A_inline((&V_2), KeyValuePair_2_get_Value_m7345512A32CB4DCAA0643050B18DC8DCD71B927A_RuntimeMethod_var);
				StringBuilder_t* L_13 = ___1_builder;
				MiniJSON_serializeString_mF0CB910410E52BC1C0F0E1A30AA0FBF1A1C48B96(L_12, L_13, NULL);
				// first = false;
				V_0 = (bool)0;
			}

IL_0056_1:
			{
				// foreach (var kv in dict)
				bool L_14;
				L_14 = Enumerator_MoveNext_mA93491D9B55547D066053F3BC0A69C635F877438((&V_1), Enumerator_MoveNext_mA93491D9B55547D066053F3BC0A69C635F877438_RuntimeMethod_var);
				if (L_14)
				{
					goto IL_0017_1;
				}
			}
			{
				goto IL_006f;
			}
		}// end try (depth: 1)
		catch(Il2CppExceptionWrapper& e)
		{
			__finallyBlock.StoreException(e.ex);
		}
	}

IL_006f:
	{
		// builder.Append("}");
		StringBuilder_t* L_15 = ___1_builder;
		NullCheck(L_15);
		StringBuilder_t* L_16;
		L_16 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_15, _stringLiteral4D8D9C94AC5DA5FCED2EC8A64E10E714A2515C30, NULL);
		// return true;
		return (bool)1;
	}
}
// System.Boolean MiniJSON::serializeArray(System.Collections.ArrayList,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_serializeArray_m50C5CB05BA9F556F76F9E3275750DADC8F13D5B2 (ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* ___0_anArray, StringBuilder_t* ___1_builder, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral758733BDBED83CBFF4F635AC26CA92AAE477F75D);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralD9691C4FD8A1F6B09DB1147CA32B442772FB46A1);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralE166C9564FBDE461738077E3B1B506525EB6ACCC);
		s_Il2CppMethodInitialized = true;
	}
	bool V_0 = false;
	int32_t V_1 = 0;
	RuntimeObject* G_B3_0 = NULL;
	RuntimeObject* G_B2_0 = NULL;
	{
		// builder.Append("[");
		StringBuilder_t* L_0 = ___1_builder;
		NullCheck(L_0);
		StringBuilder_t* L_1;
		L_1 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_0, _stringLiteralD9691C4FD8A1F6B09DB1147CA32B442772FB46A1, NULL);
		// bool first = true;
		V_0 = (bool)1;
		// for (int i = 0; i < anArray.Count; i++)
		V_1 = 0;
		goto IL_0038;
	}

IL_0012:
	{
		// object value = anArray[i];
		ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* L_2 = ___0_anArray;
		int32_t L_3 = V_1;
		NullCheck(L_2);
		RuntimeObject* L_4;
		L_4 = VirtualFuncInvoker1< RuntimeObject*, int32_t >::Invoke(25 /* System.Object System.Collections.ArrayList::get_Item(System.Int32) */, L_2, L_3);
		// if (!first)
		bool L_5 = V_0;
		G_B2_0 = L_4;
		if (L_5)
		{
			G_B3_0 = L_4;
			goto IL_0028;
		}
	}
	{
		// builder.Append(", ");
		StringBuilder_t* L_6 = ___1_builder;
		NullCheck(L_6);
		StringBuilder_t* L_7;
		L_7 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_6, _stringLiteral758733BDBED83CBFF4F635AC26CA92AAE477F75D, NULL);
		G_B3_0 = G_B2_0;
	}

IL_0028:
	{
		// if (!serializeValue(value, builder))
		StringBuilder_t* L_8 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		bool L_9;
		L_9 = MiniJSON_serializeValue_mA5FACD9C89912FCDAEF66E9919E04A67235C1D5A(G_B3_0, L_8, NULL);
		if (L_9)
		{
			goto IL_0032;
		}
	}
	{
		// return false;
		return (bool)0;
	}

IL_0032:
	{
		// first = false;
		V_0 = (bool)0;
		// for (int i = 0; i < anArray.Count; i++)
		int32_t L_10 = V_1;
		V_1 = ((int32_t)il2cpp_codegen_add(L_10, 1));
	}

IL_0038:
	{
		// for (int i = 0; i < anArray.Count; i++)
		int32_t L_11 = V_1;
		ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* L_12 = ___0_anArray;
		NullCheck(L_12);
		int32_t L_13;
		L_13 = VirtualFuncInvoker0< int32_t >::Invoke(21 /* System.Int32 System.Collections.ArrayList::get_Count() */, L_12);
		if ((((int32_t)L_11) < ((int32_t)L_13)))
		{
			goto IL_0012;
		}
	}
	{
		// builder.Append("]");
		StringBuilder_t* L_14 = ___1_builder;
		NullCheck(L_14);
		StringBuilder_t* L_15;
		L_15 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_14, _stringLiteralE166C9564FBDE461738077E3B1B506525EB6ACCC, NULL);
		// return true;
		return (bool)1;
	}
}
// System.Boolean MiniJSON::serializeValue(System.Object,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool MiniJSON_serializeValue_mA5FACD9C89912FCDAEF66E9919E04A67235C1D5A (RuntimeObject* ___0_value, StringBuilder_t* ___1_builder, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Char_t521A6F19B456D956AF452D926C32709DC03D6B17_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ICollection_t37E7B9DC5B4EF41D190D607F92835BF1171C0E8E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&String_t_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral5BEFD8CC60A79699B5BB00E37BAC5B62D371E174);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral77D38C0623F92B292B925F6E72CF5CF99A20D4EB);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralB7C45DD316C68ABF3429C20058C2981C652192F2);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (value == null)
		RuntimeObject* L_0 = ___0_value;
		if (L_0)
		{
			goto IL_0014;
		}
	}
	{
		// builder.Append("null");
		StringBuilder_t* L_1 = ___1_builder;
		NullCheck(L_1);
		StringBuilder_t* L_2;
		L_2 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_1, _stringLiteral5BEFD8CC60A79699B5BB00E37BAC5B62D371E174, NULL);
		goto IL_012e;
	}

IL_0014:
	{
		// else if (value.GetType().IsArray)
		RuntimeObject* L_3 = ___0_value;
		NullCheck(L_3);
		Type_t* L_4;
		L_4 = Object_GetType_mE10A8FC1E57F3DF29972CCBC026C2DC3942263B3(L_3, NULL);
		NullCheck(L_4);
		bool L_5;
		L_5 = Type_get_IsArray_mB9B8CA713B2AA9D6AFECC24E05AF78D22532B673(L_4, NULL);
		if (!L_5)
		{
			goto IL_0038;
		}
	}
	{
		// serializeArray(new ArrayList((ICollection)value), builder);
		RuntimeObject* L_6 = ___0_value;
		ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* L_7 = (ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A*)il2cpp_codegen_object_new(ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var);
		NullCheck(L_7);
		ArrayList__ctor_mCE5AABB7EF7F41F9170791E2C5145E49684DCE11(L_7, ((RuntimeObject*)Castclass((RuntimeObject*)L_6, ICollection_t37E7B9DC5B4EF41D190D607F92835BF1171C0E8E_il2cpp_TypeInfo_var)), NULL);
		StringBuilder_t* L_8 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		bool L_9;
		L_9 = MiniJSON_serializeArray_m50C5CB05BA9F556F76F9E3275750DADC8F13D5B2(L_7, L_8, NULL);
		goto IL_012e;
	}

IL_0038:
	{
		// else if (value is string)
		RuntimeObject* L_10 = ___0_value;
		if (!((String_t*)IsInstSealed((RuntimeObject*)L_10, String_t_il2cpp_TypeInfo_var)))
		{
			goto IL_0051;
		}
	}
	{
		// serializeString((string)value, builder);
		RuntimeObject* L_11 = ___0_value;
		StringBuilder_t* L_12 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		MiniJSON_serializeString_mF0CB910410E52BC1C0F0E1A30AA0FBF1A1C48B96(((String_t*)CastclassSealed((RuntimeObject*)L_11, String_t_il2cpp_TypeInfo_var)), L_12, NULL);
		goto IL_012e;
	}

IL_0051:
	{
		// else if (value is Char)
		RuntimeObject* L_13 = ___0_value;
		if (!((RuntimeObject*)IsInstSealed((RuntimeObject*)L_13, Char_t521A6F19B456D956AF452D926C32709DC03D6B17_il2cpp_TypeInfo_var)))
		{
			goto IL_006f;
		}
	}
	{
		// serializeString(Convert.ToString((char)value), builder);
		RuntimeObject* L_14 = ___0_value;
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		String_t* L_15;
		L_15 = Convert_ToString_m8270C8361D2796C5C4659D441829CAFEEFBAC91C(((*(Il2CppChar*)((Il2CppChar*)(Il2CppChar*)UnBox(L_14, Char_t521A6F19B456D956AF452D926C32709DC03D6B17_il2cpp_TypeInfo_var)))), NULL);
		StringBuilder_t* L_16 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		MiniJSON_serializeString_mF0CB910410E52BC1C0F0E1A30AA0FBF1A1C48B96(L_15, L_16, NULL);
		goto IL_012e;
	}

IL_006f:
	{
		// else if (value is decimal)
		RuntimeObject* L_17 = ___0_value;
		if (!((RuntimeObject*)IsInstSealed((RuntimeObject*)L_17, Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F_il2cpp_TypeInfo_var)))
		{
			goto IL_008d;
		}
	}
	{
		// serializeString(Convert.ToString((decimal)value), builder);
		RuntimeObject* L_18 = ___0_value;
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		String_t* L_19;
		L_19 = Convert_ToString_mECE1A0B3DAAA703AA05DD446204669B4B81313AE(((*(Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F*)((Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F*)(Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F*)UnBox(L_18, Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F_il2cpp_TypeInfo_var)))), NULL);
		StringBuilder_t* L_20 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		MiniJSON_serializeString_mF0CB910410E52BC1C0F0E1A30AA0FBF1A1C48B96(L_19, L_20, NULL);
		goto IL_012e;
	}

IL_008d:
	{
		// else if (value is Hashtable)
		RuntimeObject* L_21 = ___0_value;
		if (!((Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)IsInstClass((RuntimeObject*)L_21, Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var)))
		{
			goto IL_00a7;
		}
	}
	{
		// serializeObject((Hashtable)value, builder);
		RuntimeObject* L_22 = ___0_value;
		StringBuilder_t* L_23 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		bool L_24;
		L_24 = MiniJSON_serializeObject_m0242063BB8D2B4C1A0EB136B4EC3E1F7A37948B6(((Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)CastclassClass((RuntimeObject*)L_22, Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var)), L_23, NULL);
		goto IL_012e;
	}

IL_00a7:
	{
		// else if (value is Dictionary<string, string>)
		RuntimeObject* L_25 = ___0_value;
		if (!((Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83*)IsInstClass((RuntimeObject*)L_25, Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83_il2cpp_TypeInfo_var)))
		{
			goto IL_00be;
		}
	}
	{
		// serializeDictionary((Dictionary<string, string>)value, builder);
		RuntimeObject* L_26 = ___0_value;
		StringBuilder_t* L_27 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		bool L_28;
		L_28 = MiniJSON_serializeDictionary_m99EA287B2265F8C5B00FE3FB0EB294A56BA374DB(((Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83*)CastclassClass((RuntimeObject*)L_26, Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83_il2cpp_TypeInfo_var)), L_27, NULL);
		goto IL_012e;
	}

IL_00be:
	{
		// else if (value is ArrayList)
		RuntimeObject* L_29 = ___0_value;
		if (!((ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A*)IsInstClass((RuntimeObject*)L_29, ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var)))
		{
			goto IL_00d5;
		}
	}
	{
		// serializeArray((ArrayList)value, builder);
		RuntimeObject* L_30 = ___0_value;
		StringBuilder_t* L_31 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		bool L_32;
		L_32 = MiniJSON_serializeArray_m50C5CB05BA9F556F76F9E3275750DADC8F13D5B2(((ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A*)CastclassClass((RuntimeObject*)L_30, ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var)), L_31, NULL);
		goto IL_012e;
	}

IL_00d5:
	{
		// else if ((value is Boolean) && ((Boolean)value == true))
		RuntimeObject* L_33 = ___0_value;
		if (!((RuntimeObject*)IsInstSealed((RuntimeObject*)L_33, Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var)))
		{
			goto IL_00f3;
		}
	}
	{
		RuntimeObject* L_34 = ___0_value;
		if (!((*(bool*)((bool*)(bool*)UnBox(L_34, Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var)))))
		{
			goto IL_00f3;
		}
	}
	{
		// builder.Append("true");
		StringBuilder_t* L_35 = ___1_builder;
		NullCheck(L_35);
		StringBuilder_t* L_36;
		L_36 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_35, _stringLiteralB7C45DD316C68ABF3429C20058C2981C652192F2, NULL);
		goto IL_012e;
	}

IL_00f3:
	{
		// else if ((value is Boolean) && ((Boolean)value == false))
		RuntimeObject* L_37 = ___0_value;
		if (!((RuntimeObject*)IsInstSealed((RuntimeObject*)L_37, Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var)))
		{
			goto IL_0111;
		}
	}
	{
		RuntimeObject* L_38 = ___0_value;
		if (((*(bool*)((bool*)(bool*)UnBox(L_38, Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var)))))
		{
			goto IL_0111;
		}
	}
	{
		// builder.Append("false");
		StringBuilder_t* L_39 = ___1_builder;
		NullCheck(L_39);
		StringBuilder_t* L_40;
		L_40 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_39, _stringLiteral77D38C0623F92B292B925F6E72CF5CF99A20D4EB, NULL);
		goto IL_012e;
	}

IL_0111:
	{
		// else if (value.GetType().IsPrimitive)
		RuntimeObject* L_41 = ___0_value;
		NullCheck(L_41);
		Type_t* L_42;
		L_42 = Object_GetType_mE10A8FC1E57F3DF29972CCBC026C2DC3942263B3(L_41, NULL);
		NullCheck(L_42);
		bool L_43;
		L_43 = Type_get_IsPrimitive_m46ACAAB8F754D37713E3E45437705F4F58FAFA18(L_42, NULL);
		if (!L_43)
		{
			goto IL_012c;
		}
	}
	{
		// serializeNumber(Convert.ToDouble(value), builder);
		RuntimeObject* L_44 = ___0_value;
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		double L_45;
		L_45 = Convert_ToDouble_m86FF4F837721833186E883102C056A35F0860EB0(L_44, NULL);
		StringBuilder_t* L_46 = ___1_builder;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		MiniJSON_serializeNumber_mC2747CEB3ABAD1CAE7755B59B6A6737FBE7A66FC(L_45, L_46, NULL);
		goto IL_012e;
	}

IL_012c:
	{
		// return false;
		return (bool)0;
	}

IL_012e:
	{
		// return true;
		return (bool)1;
	}
}
// System.Void MiniJSON::serializeString(System.String,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void MiniJSON_serializeString_mF0CB910410E52BC1C0F0E1A30AA0FBF1A1C48B96 (String_t* ___0_aString, StringBuilder_t* ___1_builder, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral5962E944D7340CE47999BF097B4AFD70C1501FB9);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral785F17F45C331C415D0A7458E6AAC36966399C51);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral7F3238CD8C342B06FB9AB185C610175C84625462);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral848E5ED630B3142F565DD995C6E8D30187ED33CD);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralA7C3FCA8C63E127B542B38A5CA5E3FEEDDD1B122);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralB78F235D4291950A7D101307609C259F3E1F033F);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralC62C64F00567C5368CAE37F4E64E1E82FF785677);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA666908BB15F4E1D2649752EC5DCBD0D5C64699);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralF18840F490E42D3CE48CDCBF47229C1C240F8ABE);
		s_Il2CppMethodInitialized = true;
	}
	CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* V_0 = NULL;
	int32_t V_1 = 0;
	Il2CppChar V_2 = 0x0;
	int32_t V_3 = 0;
	{
		// builder.Append("\"");
		StringBuilder_t* L_0 = ___1_builder;
		NullCheck(L_0);
		StringBuilder_t* L_1;
		L_1 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_0, _stringLiteralC62C64F00567C5368CAE37F4E64E1E82FF785677, NULL);
		// char[] charArray = aString.ToCharArray();
		String_t* L_2 = ___0_aString;
		NullCheck(L_2);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_3;
		L_3 = String_ToCharArray_m0699A92AA3E744229EF29CB9D943C47DF4FE5B46(L_2, NULL);
		V_0 = L_3;
		// for (int i = 0; i < charArray.Length; i++)
		V_1 = 0;
		goto IL_00eb;
	}

IL_001a:
	{
		// char c = charArray[i];
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_4 = V_0;
		int32_t L_5 = V_1;
		NullCheck(L_4);
		int32_t L_6 = L_5;
		uint16_t L_7 = (uint16_t)(L_4)->GetAt(static_cast<il2cpp_array_size_t>(L_6));
		V_2 = L_7;
		// if (c == '"')
		Il2CppChar L_8 = V_2;
		if ((!(((uint32_t)L_8) == ((uint32_t)((int32_t)34)))))
		{
			goto IL_0034;
		}
	}
	{
		// builder.Append("\\\"");
		StringBuilder_t* L_9 = ___1_builder;
		NullCheck(L_9);
		StringBuilder_t* L_10;
		L_10 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_9, _stringLiteral848E5ED630B3142F565DD995C6E8D30187ED33CD, NULL);
		goto IL_00e7;
	}

IL_0034:
	{
		// else if (c == '\\')
		Il2CppChar L_11 = V_2;
		if ((!(((uint32_t)L_11) == ((uint32_t)((int32_t)92)))))
		{
			goto IL_004a;
		}
	}
	{
		// builder.Append("\\\\");
		StringBuilder_t* L_12 = ___1_builder;
		NullCheck(L_12);
		StringBuilder_t* L_13;
		L_13 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_12, _stringLiteralF18840F490E42D3CE48CDCBF47229C1C240F8ABE, NULL);
		goto IL_00e7;
	}

IL_004a:
	{
		// else if (c == '\b')
		Il2CppChar L_14 = V_2;
		if ((!(((uint32_t)L_14) == ((uint32_t)8))))
		{
			goto IL_005f;
		}
	}
	{
		// builder.Append("\\b");
		StringBuilder_t* L_15 = ___1_builder;
		NullCheck(L_15);
		StringBuilder_t* L_16;
		L_16 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_15, _stringLiteral5962E944D7340CE47999BF097B4AFD70C1501FB9, NULL);
		goto IL_00e7;
	}

IL_005f:
	{
		// else if (c == '\f')
		Il2CppChar L_17 = V_2;
		if ((!(((uint32_t)L_17) == ((uint32_t)((int32_t)12)))))
		{
			goto IL_0072;
		}
	}
	{
		// builder.Append("\\f");
		StringBuilder_t* L_18 = ___1_builder;
		NullCheck(L_18);
		StringBuilder_t* L_19;
		L_19 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_18, _stringLiteralA7C3FCA8C63E127B542B38A5CA5E3FEEDDD1B122, NULL);
		goto IL_00e7;
	}

IL_0072:
	{
		// else if (c == '\n')
		Il2CppChar L_20 = V_2;
		if ((!(((uint32_t)L_20) == ((uint32_t)((int32_t)10)))))
		{
			goto IL_0085;
		}
	}
	{
		// builder.Append("\\n");
		StringBuilder_t* L_21 = ___1_builder;
		NullCheck(L_21);
		StringBuilder_t* L_22;
		L_22 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_21, _stringLiteral785F17F45C331C415D0A7458E6AAC36966399C51, NULL);
		goto IL_00e7;
	}

IL_0085:
	{
		// else if (c == '\r')
		Il2CppChar L_23 = V_2;
		if ((!(((uint32_t)L_23) == ((uint32_t)((int32_t)13)))))
		{
			goto IL_0098;
		}
	}
	{
		// builder.Append("\\r");
		StringBuilder_t* L_24 = ___1_builder;
		NullCheck(L_24);
		StringBuilder_t* L_25;
		L_25 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_24, _stringLiteralB78F235D4291950A7D101307609C259F3E1F033F, NULL);
		goto IL_00e7;
	}

IL_0098:
	{
		// else if (c == '\t')
		Il2CppChar L_26 = V_2;
		if ((!(((uint32_t)L_26) == ((uint32_t)((int32_t)9)))))
		{
			goto IL_00ab;
		}
	}
	{
		// builder.Append("\\t");
		StringBuilder_t* L_27 = ___1_builder;
		NullCheck(L_27);
		StringBuilder_t* L_28;
		L_28 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_27, _stringLiteral7F3238CD8C342B06FB9AB185C610175C84625462, NULL);
		goto IL_00e7;
	}

IL_00ab:
	{
		// int codepoint = Convert.ToInt32(c);
		Il2CppChar L_29 = V_2;
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		int32_t L_30;
		L_30 = Convert_ToInt32_mDBBE9318A7CCE1560974CE93F5BFED9931CF0052(L_29, NULL);
		V_3 = L_30;
		// if ((codepoint >= 32) && (codepoint <= 126))
		int32_t L_31 = V_3;
		if ((((int32_t)L_31) < ((int32_t)((int32_t)32))))
		{
			goto IL_00c6;
		}
	}
	{
		int32_t L_32 = V_3;
		if ((((int32_t)L_32) > ((int32_t)((int32_t)126))))
		{
			goto IL_00c6;
		}
	}
	{
		// builder.Append(c);
		StringBuilder_t* L_33 = ___1_builder;
		Il2CppChar L_34 = V_2;
		NullCheck(L_33);
		StringBuilder_t* L_35;
		L_35 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_33, L_34, NULL);
		goto IL_00e7;
	}

IL_00c6:
	{
		// builder.Append("\\u" + Convert.ToString(codepoint, 16).PadLeft(4, '0'));
		StringBuilder_t* L_36 = ___1_builder;
		int32_t L_37 = V_3;
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		String_t* L_38;
		L_38 = Convert_ToString_mC3349029FE37EB00B5BFCB1F87022458A3834E35(L_37, ((int32_t)16), NULL);
		NullCheck(L_38);
		String_t* L_39;
		L_39 = String_PadLeft_m99DDD242908E78B71E9631EE66331E8A130EB31F(L_38, 4, ((int32_t)48), NULL);
		String_t* L_40;
		L_40 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(_stringLiteralDA666908BB15F4E1D2649752EC5DCBD0D5C64699, L_39, NULL);
		NullCheck(L_36);
		StringBuilder_t* L_41;
		L_41 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_36, L_40, NULL);
	}

IL_00e7:
	{
		// for (int i = 0; i < charArray.Length; i++)
		int32_t L_42 = V_1;
		V_1 = ((int32_t)il2cpp_codegen_add(L_42, 1));
	}

IL_00eb:
	{
		// for (int i = 0; i < charArray.Length; i++)
		int32_t L_43 = V_1;
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_44 = V_0;
		NullCheck(L_44);
		if ((((int32_t)L_43) < ((int32_t)((int32_t)(((RuntimeArray*)L_44)->max_length)))))
		{
			goto IL_001a;
		}
	}
	{
		// builder.Append("\"");
		StringBuilder_t* L_45 = ___1_builder;
		NullCheck(L_45);
		StringBuilder_t* L_46;
		L_46 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_45, _stringLiteralC62C64F00567C5368CAE37F4E64E1E82FF785677, NULL);
		// }
		return;
	}
}
// System.Void MiniJSON::serializeNumber(System.Double,System.Text.StringBuilder)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void MiniJSON_serializeNumber_mC2747CEB3ABAD1CAE7755B59B6A6737FBE7A66FC (double ___0_number, StringBuilder_t* ___1_builder, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// builder.Append(Convert.ToString(number)); // , CultureInfo.InvariantCulture));
		StringBuilder_t* L_0 = ___1_builder;
		double L_1 = ___0_number;
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		String_t* L_2;
		L_2 = Convert_ToString_mA5896BBF6FFE5A37B531E8E2F5258AE6F0C38580(L_1, NULL);
		NullCheck(L_0);
		StringBuilder_t* L_3;
		L_3 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_0, L_2, NULL);
		// }
		return;
	}
}
// System.Void MiniJSON::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void MiniJSON__ctor_m3873CB585E9D31F7C486AA6DBDE83FBCA3AB7327 (MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
// System.Void MiniJSON::.cctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void MiniJSON__cctor_m4DF2C230052B171578B1A4441764C057B43153AE (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709);
		s_Il2CppMethodInitialized = true;
	}
	{
		// protected static int lastErrorIndex = -1;
		((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastErrorIndex_13 = (-1);
		// protected static string lastDecode = "";
		((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastDecode_14 = _stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709;
		Il2CppCodeGenWriteBarrier((void**)(&((MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_StaticFields*)il2cpp_codegen_static_fields_for(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var))->___lastDecode_14), (void*)_stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709);
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.String MiniJsonExtensions::toJson(System.Collections.Hashtable)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* MiniJsonExtensions_toJson_m8F36C0A842C0EF15C821B60A49168407F0AD7CF7 (Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// return MiniJSON.jsonEncode(obj);
		Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* L_0 = ___0_obj;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		String_t* L_1;
		L_1 = MiniJSON_jsonEncode_m65A3BEB6F15FD860F02D7160D6B404AD34DB136F(L_0, NULL);
		return L_1;
	}
}
// System.String MiniJsonExtensions::toJson(System.Collections.Generic.Dictionary`2<System.String,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* MiniJsonExtensions_toJson_mECB1B97F82ED077717243EE7B7ABCC80EC1376CF (Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// return MiniJSON.jsonEncode(obj);
		Dictionary_2_t46B2DB028096FA2B828359E52F37F3105A83AD83* L_0 = ___0_obj;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		String_t* L_1;
		L_1 = MiniJSON_jsonEncode_m65A3BEB6F15FD860F02D7160D6B404AD34DB136F(L_0, NULL);
		return L_1;
	}
}
// System.Collections.ArrayList MiniJsonExtensions::arrayListFromJson(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A* MiniJsonExtensions_arrayListFromJson_mC4C17F21F8A38A3AA12F753AF4D6D8DB41587874 (String_t* ___0_json, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// return MiniJSON.jsonDecode(json) as ArrayList;
		String_t* L_0 = ___0_json;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		RuntimeObject* L_1;
		L_1 = MiniJSON_jsonDecode_m8D73840A67B22A1B7EC331B43E639EEAE4243997(L_0, NULL);
		return ((ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A*)IsInstClass((RuntimeObject*)L_1, ArrayList_t7A8E5AF0C4378015B5731ABE2BED8F2782FEEF8A_il2cpp_TypeInfo_var));
	}
}
// System.Collections.Hashtable MiniJsonExtensions::hashtableFromJson(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D* MiniJsonExtensions_hashtableFromJson_m3D07B3D913BF04E5C4E8DDC1FED2169007DB5947 (String_t* ___0_json, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// return MiniJSON.jsonDecode(json) as Hashtable;
		String_t* L_0 = ___0_json;
		il2cpp_codegen_runtime_class_init_inline(MiniJSON_t19E457D520EE5621F744C213588CFC7F61DEC71E_il2cpp_TypeInfo_var);
		RuntimeObject* L_1;
		L_1 = MiniJSON_jsonDecode_m8D73840A67B22A1B7EC331B43E639EEAE4243997(L_0, NULL);
		return ((Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D*)IsInstClass((RuntimeObject*)L_1, Hashtable_tEFC3B6496E6747787D8BB761B51F2AE3A8CFFE2D_il2cpp_TypeInfo_var));
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Object RiseJson::Deserialize(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* RiseJson_Deserialize_m414C29CB419E05EB6BDD7196CA5BD5CB448E516B (String_t* ___0_json, const RuntimeMethod* method) 
{
	{
		// if (json == null)
		String_t* L_0 = ___0_json;
		if (L_0)
		{
			goto IL_0005;
		}
	}
	{
		// return null;
		return NULL;
	}

IL_0005:
	{
		// return Parser.Parse(json);
		String_t* L_1 = ___0_json;
		RuntimeObject* L_2;
		L_2 = Parser_Parse_m4D6309FAC6682FB99C264A5EB0FEDEB4E0F068A1(L_1, NULL);
		return L_2;
	}
}
// System.String RiseJson::Serialize(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* RiseJson_Serialize_mC8D5B56AD866D06AA9D83ED82F9A3B29536594F2 (RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	{
		// return Serializer.Serialize(obj);
		RuntimeObject* L_0 = ___0_obj;
		String_t* L_1;
		L_1 = Serializer_Serialize_mC3EB9A1F672CE188D04899BD2E45C7DBDD33E2B8(L_0, NULL);
		return L_1;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseJson/Parser::.ctor(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Parser__ctor_m71515B6F998B14172E3C14C3F05349D764B45B01 (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, String_t* ___0_jsonString, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// private Parser(string jsonString)
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		// this.json = new StringReader(jsonString);
		String_t* L_0 = ___0_jsonString;
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_1 = (StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8*)il2cpp_codegen_object_new(StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8_il2cpp_TypeInfo_var);
		NullCheck(L_1);
		StringReader__ctor_m72556EC1062F49E05CF41B0825AC7FA2DB2A81C0(L_1, L_0, NULL);
		__this->___json_1 = L_1;
		Il2CppCodeGenWriteBarrier((void**)(&__this->___json_1), (void*)L_1);
		// }
		return;
	}
}
// System.Void RiseJson/Parser::Dispose()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Parser_Dispose_mC19DA17217220648B0F108C193667EEFB1BAE1B4 (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	{
		// this.json.Dispose();
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_0 = __this->___json_1;
		NullCheck(L_0);
		TextReader_Dispose_mDCB332EFA06970A9CC7EC4596FCC5220B9512616(L_0, NULL);
		// this.json = null;
		__this->___json_1 = (StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8*)NULL;
		Il2CppCodeGenWriteBarrier((void**)(&__this->___json_1), (void*)(StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8*)NULL);
		// }
		return;
	}
}
// System.Void RiseJson/Parser::EatWhitespace()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Parser_EatWhitespace_m75DEE6C4273E95B5CEDC45964350830BE7C3C79C (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralEBC658B067B5C785A3F0BB67D73755F6FEE7F70C);
		s_Il2CppMethodInitialized = true;
	}
	{
		goto IL_001c;
	}

IL_0002:
	{
		// this.json.Read();
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_0 = __this->___json_1;
		NullCheck(L_0);
		int32_t L_1;
		L_1 = VirtualFuncInvoker0< int32_t >::Invoke(9 /* System.Int32 System.IO.TextReader::Read() */, L_0);
		// if (this.json.Peek() == -1)
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_2 = __this->___json_1;
		NullCheck(L_2);
		int32_t L_3;
		L_3 = VirtualFuncInvoker0< int32_t >::Invoke(8 /* System.Int32 System.IO.TextReader::Peek() */, L_2);
		if ((((int32_t)L_3) == ((int32_t)(-1))))
		{
			goto IL_002f;
		}
	}

IL_001c:
	{
		// while (" \t\n\r".IndexOf(this.PeekChar) != -1)
		Il2CppChar L_4;
		L_4 = Parser_get_PeekChar_m896892C8D72C5D3B83B13D29DC10A043A5B0ACEC(__this, NULL);
		NullCheck(_stringLiteralEBC658B067B5C785A3F0BB67D73755F6FEE7F70C);
		int32_t L_5;
		L_5 = String_IndexOf_mE21E78F35EF4A7768E385A72814C88D22B689966(_stringLiteralEBC658B067B5C785A3F0BB67D73755F6FEE7F70C, L_4, NULL);
		if ((!(((uint32_t)L_5) == ((uint32_t)(-1)))))
		{
			goto IL_0002;
		}
	}

IL_002f:
	{
		// }
		return;
	}
}
// System.Object RiseJson/Parser::Parse(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* Parser_Parse_m4D6309FAC6682FB99C264A5EB0FEDEB4E0F068A1 (String_t* ___0_jsonString, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* V_0 = NULL;
	RuntimeObject* V_1 = NULL;
	{
		// using (RiseJson.Parser parser = new RiseJson.Parser(jsonString))
		String_t* L_0 = ___0_jsonString;
		Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* L_1 = (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0*)il2cpp_codegen_object_new(Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_il2cpp_TypeInfo_var);
		NullCheck(L_1);
		Parser__ctor_m71515B6F998B14172E3C14C3F05349D764B45B01(L_1, L_0, NULL);
		V_0 = L_1;
	}
	{
		auto __finallyBlock = il2cpp::utils::Finally([&]
		{

FINALLY_0010:
			{// begin finally (depth: 1)
				{
					Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* L_2 = V_0;
					if (!L_2)
					{
						goto IL_0019;
					}
				}
				{
					Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* L_3 = V_0;
					NullCheck(L_3);
					InterfaceActionInvoker0::Invoke(0 /* System.Void System.IDisposable::Dispose() */, IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var, L_3);
				}

IL_0019:
				{
					return;
				}
			}// end finally (depth: 1)
		});
		try
		{// begin try (depth: 1)
			// return parser.ParseValue();
			Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* L_4 = V_0;
			NullCheck(L_4);
			RuntimeObject* L_5;
			L_5 = Parser_ParseValue_m30DCD24F852EDA784F16ED41833D03EB4C8D9C3F(L_4, NULL);
			V_1 = L_5;
			goto IL_001a;
		}// end try (depth: 1)
		catch(Il2CppExceptionWrapper& e)
		{
			__finallyBlock.StoreException(e.ex);
		}
	}

IL_001a:
	{
		// }
		RuntimeObject* L_6 = V_1;
		return L_6;
	}
}
// System.Collections.Generic.List`1<System.Object> RiseJson/Parser::ParseArray()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* Parser_ParseArray_mD10428D7BF3D9FA03C0CB739296936A2B6B1FCBA (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&List_1_Add_mEBCF994CC3814631017F46A387B1A192ED6C85C7_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&List_1__ctor_m7F078BB342729BDF11327FD89D7872265328F690_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* V_0 = NULL;
	bool V_1 = false;
	int32_t V_2 = 0;
	RuntimeObject* V_3 = NULL;
	{
		// List<object> list = new List<object>();
		List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* L_0 = (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D*)il2cpp_codegen_object_new(List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		List_1__ctor_m7F078BB342729BDF11327FD89D7872265328F690(L_0, List_1__ctor_m7F078BB342729BDF11327FD89D7872265328F690_RuntimeMethod_var);
		V_0 = L_0;
		// this.json.Read();
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_1 = __this->___json_1;
		NullCheck(L_1);
		int32_t L_2;
		L_2 = VirtualFuncInvoker0< int32_t >::Invoke(9 /* System.Int32 System.IO.TextReader::Read() */, L_1);
		// bool flag = true;
		V_1 = (bool)1;
		goto IL_003f;
	}

IL_0016:
	{
		// TOKEN nextSymbol = this.NextSymbol;
		int32_t L_3;
		L_3 = Parser_get_NextSymbol_mE4BC8E813CC48B6E5CD7F6C5679AD163723A4E90(__this, NULL);
		V_2 = L_3;
		int32_t L_4 = V_2;
		if (!L_4)
		{
			goto IL_002e;
		}
	}
	{
		int32_t L_5 = V_2;
		if ((((int32_t)L_5) == ((int32_t)4)))
		{
			goto IL_002a;
		}
	}
	{
		int32_t L_6 = V_2;
		if ((((int32_t)L_6) == ((int32_t)6)))
		{
			goto IL_003f;
		}
	}
	{
		goto IL_0030;
	}

IL_002a:
	{
		// flag = false;
		V_1 = (bool)0;
		// continue;
		goto IL_003f;
	}

IL_002e:
	{
		// return null;
		return (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D*)NULL;
	}

IL_0030:
	{
		// object item = this.ParseBySymbol(nextSymbol);
		int32_t L_7 = V_2;
		RuntimeObject* L_8;
		L_8 = Parser_ParseBySymbol_mF0126896D2623317331073FF78E51B6933356B8B(__this, L_7, NULL);
		V_3 = L_8;
		// list.Add(item);
		List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* L_9 = V_0;
		RuntimeObject* L_10 = V_3;
		NullCheck(L_9);
		List_1_Add_mEBCF994CC3814631017F46A387B1A192ED6C85C7_inline(L_9, L_10, List_1_Add_mEBCF994CC3814631017F46A387B1A192ED6C85C7_RuntimeMethod_var);
	}

IL_003f:
	{
		// while (flag)
		bool L_11 = V_1;
		if (L_11)
		{
			goto IL_0016;
		}
	}
	{
		// return list;
		List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* L_12 = V_0;
		return L_12;
	}
}
// System.Object RiseJson/Parser::ParseBySymbol(RiseJson/Parser/TOKEN)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* Parser_ParseBySymbol_mF0126896D2623317331073FF78E51B6933356B8B (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, int32_t ___0_symbol, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		int32_t L_0 = ___0_symbol;
		switch (((int32_t)il2cpp_codegen_subtract((int32_t)L_0, 1)))
		{
			case 0:
			{
				goto IL_0036;
			}
			case 1:
			{
				goto IL_0062;
			}
			case 2:
			{
				goto IL_003d;
			}
			case 3:
			{
				goto IL_0062;
			}
			case 4:
			{
				goto IL_0062;
			}
			case 5:
			{
				goto IL_0062;
			}
			case 6:
			{
				goto IL_0044;
			}
			case 7:
			{
				goto IL_004b;
			}
			case 8:
			{
				goto IL_0052;
			}
			case 9:
			{
				goto IL_0059;
			}
			case 10:
			{
				goto IL_0060;
			}
		}
	}
	{
		goto IL_0062;
	}

IL_0036:
	{
		// return this.ParseObject();
		Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710* L_1;
		L_1 = Parser_ParseObject_m97A3AC834623AED937221ADDEA17F7810EE1CB8A(__this, NULL);
		return L_1;
	}

IL_003d:
	{
		// return this.ParseArray();
		List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* L_2;
		L_2 = Parser_ParseArray_mD10428D7BF3D9FA03C0CB739296936A2B6B1FCBA(__this, NULL);
		return L_2;
	}

IL_0044:
	{
		// return this.ParseString();
		String_t* L_3;
		L_3 = Parser_ParseString_m3A36E1EC5CE8E0B3879B83FE7B26027712ABF31C(__this, NULL);
		return L_3;
	}

IL_004b:
	{
		// return this.ParseNumber();
		RuntimeObject* L_4;
		L_4 = Parser_ParseNumber_m2125CE3D44411D35A69EDC7872224742581EF39C(__this, NULL);
		return L_4;
	}

IL_0052:
	{
		// return true;
		bool L_5 = ((bool)1);
		RuntimeObject* L_6 = Box(Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var, &L_5);
		return L_6;
	}

IL_0059:
	{
		// return false;
		bool L_7 = ((bool)0);
		RuntimeObject* L_8 = Box(Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var, &L_7);
		return L_8;
	}

IL_0060:
	{
		// return null;
		return NULL;
	}

IL_0062:
	{
		// return null;
		return NULL;
	}
}
// System.Object RiseJson/Parser::ParseNumber()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* Parser_ParseNumber_m2125CE3D44411D35A69EDC7872224742581EF39C (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Double_tE150EF3D1D43DEE85D533810AB4C742307EEDE5F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Int64_t092CFB123BE63C28ACDAF65C68F21A526050DBA3_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	double V_0 = 0.0;
	String_t* V_1 = NULL;
	int64_t V_2 = 0;
	{
		// string nextWord = this.NextWord;
		String_t* L_0;
		L_0 = Parser_get_NextWord_m01A4B42AEFAF4FD146911805717426D50F33266C(__this, NULL);
		V_1 = L_0;
		// if (nextWord.IndexOf('.') == -1)
		String_t* L_1 = V_1;
		NullCheck(L_1);
		int32_t L_2;
		L_2 = String_IndexOf_mE21E78F35EF4A7768E385A72814C88D22B689966(L_1, ((int32_t)46), NULL);
		if ((!(((uint32_t)L_2) == ((uint32_t)(-1)))))
		{
			goto IL_0022;
		}
	}
	{
		// long.TryParse(nextWord, out num);
		String_t* L_3 = V_1;
		bool L_4;
		L_4 = Int64_TryParse_m3FC0128C89CC2331239FC2A0A749BF33455F03D2(L_3, (&V_2), NULL);
		// return num;
		int64_t L_5 = V_2;
		int64_t L_6 = L_5;
		RuntimeObject* L_7 = Box(Int64_t092CFB123BE63C28ACDAF65C68F21A526050DBA3_il2cpp_TypeInfo_var, &L_6);
		return L_7;
	}

IL_0022:
	{
		// double.TryParse(nextWord, out num2);
		String_t* L_8 = V_1;
		bool L_9;
		L_9 = Double_TryParse_m60AD55BC181D70F661BC2A2294E66B5466C3C018(L_8, (&V_0), NULL);
		// return num2;
		double L_10 = V_0;
		double L_11 = L_10;
		RuntimeObject* L_12 = Box(Double_tE150EF3D1D43DEE85D533810AB4C742307EEDE5F_il2cpp_TypeInfo_var, &L_11);
		return L_12;
	}
}
// System.Collections.Generic.Dictionary`2<System.String,System.Object> RiseJson/Parser::ParseObject()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710* Parser_ParseObject_m97A3AC834623AED937221ADDEA17F7810EE1CB8A (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Dictionary_2__ctor_mC4F3DF292BAD88F4BF193C49CD689FAEBC4570A9_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Dictionary_2_set_Item_m7CCA97075B48AFB2B97E5A072B94BC7679374341_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710* V_0 = NULL;
	int32_t V_1 = 0;
	String_t* V_2 = NULL;
	{
		// Dictionary<string, object> dictionary = new Dictionary<string, object>();
		Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710* L_0 = (Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710*)il2cpp_codegen_object_new(Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		Dictionary_2__ctor_mC4F3DF292BAD88F4BF193C49CD689FAEBC4570A9(L_0, Dictionary_2__ctor_mC4F3DF292BAD88F4BF193C49CD689FAEBC4570A9_RuntimeMethod_var);
		V_0 = L_0;
		// this.json.Read();
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_1 = __this->___json_1;
		NullCheck(L_1);
		int32_t L_2;
		L_2 = VirtualFuncInvoker0< int32_t >::Invoke(9 /* System.Int32 System.IO.TextReader::Read() */, L_1);
	}

IL_0012:
	{
		// TOKEN nextSymbol = this.NextSymbol;
		int32_t L_3;
		L_3 = Parser_get_NextSymbol_mE4BC8E813CC48B6E5CD7F6C5679AD163723A4E90(__this, NULL);
		V_1 = L_3;
		int32_t L_4 = V_1;
		if (!L_4)
		{
			goto IL_0022;
		}
	}
	{
		int32_t L_5 = V_1;
		if ((((int32_t)L_5) == ((int32_t)2)))
		{
			goto IL_0024;
		}
	}
	{
		goto IL_0026;
	}

IL_0022:
	{
		// return null;
		return (Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710*)NULL;
	}

IL_0024:
	{
		// return dictionary;
		Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710* L_6 = V_0;
		return L_6;
	}

IL_0026:
	{
		// if (nextSymbol != TOKEN.COMMA)
		int32_t L_7 = V_1;
		if ((((int32_t)L_7) == ((int32_t)6)))
		{
			goto IL_0012;
		}
	}
	{
		// string str = this.ParseString();
		String_t* L_8;
		L_8 = Parser_ParseString_m3A36E1EC5CE8E0B3879B83FE7B26027712ABF31C(__this, NULL);
		V_2 = L_8;
		// if (str == null)
		String_t* L_9 = V_2;
		if (L_9)
		{
			goto IL_0036;
		}
	}
	{
		// return null;
		return (Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710*)NULL;
	}

IL_0036:
	{
		// if (this.NextSymbol != TOKEN.COLON)
		int32_t L_10;
		L_10 = Parser_get_NextSymbol_mE4BC8E813CC48B6E5CD7F6C5679AD163723A4E90(__this, NULL);
		if ((((int32_t)L_10) == ((int32_t)5)))
		{
			goto IL_0041;
		}
	}
	{
		// return null;
		return (Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710*)NULL;
	}

IL_0041:
	{
		// this.json.Read();
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_11 = __this->___json_1;
		NullCheck(L_11);
		int32_t L_12;
		L_12 = VirtualFuncInvoker0< int32_t >::Invoke(9 /* System.Int32 System.IO.TextReader::Read() */, L_11);
		// dictionary[str] = this.ParseValue();
		Dictionary_2_tA348003A3C1CEFB3096E9D2A0BC7F1AC8EC4F710* L_13 = V_0;
		String_t* L_14 = V_2;
		RuntimeObject* L_15;
		L_15 = Parser_ParseValue_m30DCD24F852EDA784F16ED41833D03EB4C8D9C3F(__this, NULL);
		NullCheck(L_13);
		Dictionary_2_set_Item_m7CCA97075B48AFB2B97E5A072B94BC7679374341(L_13, L_14, L_15, Dictionary_2_set_Item_m7CCA97075B48AFB2B97E5A072B94BC7679374341_RuntimeMethod_var);
		// while (true)
		goto IL_0012;
	}
}
// System.String RiseJson/Parser::ParseString()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Parser_ParseString_m3A36E1EC5CE8E0B3879B83FE7B26027712ABF31C (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&StringBuilder_t_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	StringBuilder_t* V_0 = NULL;
	bool V_1 = false;
	StringBuilder_t* V_2 = NULL;
	int32_t V_3 = 0;
	Il2CppChar V_4 = 0x0;
	Il2CppChar V_5 = 0x0;
	Il2CppChar V_6 = 0x0;
	{
		// StringBuilder builder = new StringBuilder();
		StringBuilder_t* L_0 = (StringBuilder_t*)il2cpp_codegen_object_new(StringBuilder_t_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		StringBuilder__ctor_m1D99713357DE05DAFA296633639DB55F8C30587D(L_0, NULL);
		V_0 = L_0;
		// this.json.Read();
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_1 = __this->___json_1;
		NullCheck(L_1);
		int32_t L_2;
		L_2 = VirtualFuncInvoker0< int32_t >::Invoke(9 /* System.Int32 System.IO.TextReader::Read() */, L_1);
		// bool flag = true;
		V_1 = (bool)1;
		goto IL_013d;
	}

IL_0019:
	{
		// StringBuilder builder2 = null;
		V_2 = (StringBuilder_t*)NULL;
		// int num = 0;
		V_3 = 0;
		// if (this.json.Peek() == -1)
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_3 = __this->___json_1;
		NullCheck(L_3);
		int32_t L_4;
		L_4 = VirtualFuncInvoker0< int32_t >::Invoke(8 /* System.Int32 System.IO.TextReader::Peek() */, L_3);
		if ((!(((uint32_t)L_4) == ((uint32_t)(-1)))))
		{
			goto IL_0032;
		}
	}
	{
		// flag = false;
		V_1 = (bool)0;
		// break;
		goto IL_0143;
	}

IL_0032:
	{
		// char nextChar = this.NextChar;
		Il2CppChar L_5;
		L_5 = Parser_get_NextChar_m0BB5BC3A8C52E251EE99E8B3FCA5D205CF66759B(__this, NULL);
		V_4 = L_5;
		// char ch2 = nextChar;
		Il2CppChar L_6 = V_4;
		V_5 = L_6;
		// if (ch2 == '"')
		Il2CppChar L_7 = V_5;
		if ((!(((uint32_t)L_7) == ((uint32_t)((int32_t)34)))))
		{
			goto IL_004b;
		}
	}
	{
		// flag = false;
		V_1 = (bool)0;
		// continue;
		goto IL_013d;
	}

IL_004b:
	{
		// if (ch2 != '\\')
		Il2CppChar L_8 = V_5;
		if ((!(((uint32_t)L_8) == ((uint32_t)((int32_t)92)))))
		{
			goto IL_0134;
		}
	}
	{
		// if (this.json.Peek() == -1)
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_9 = __this->___json_1;
		NullCheck(L_9);
		int32_t L_10;
		L_10 = VirtualFuncInvoker0< int32_t >::Invoke(8 /* System.Int32 System.IO.TextReader::Peek() */, L_9);
		if ((!(((uint32_t)L_10) == ((uint32_t)(-1)))))
		{
			goto IL_0069;
		}
	}
	{
		// flag = false;
		V_1 = (bool)0;
		// continue;
		goto IL_013d;
	}

IL_0069:
	{
		// nextChar = this.NextChar;
		Il2CppChar L_11;
		L_11 = Parser_get_NextChar_m0BB5BC3A8C52E251EE99E8B3FCA5D205CF66759B(__this, NULL);
		V_4 = L_11;
		// char ch3 = nextChar;
		Il2CppChar L_12 = V_4;
		V_6 = L_12;
		Il2CppChar L_13 = V_6;
		if ((((int32_t)L_13) == ((int32_t)((int32_t)110))))
		{
			goto IL_0097;
		}
	}
	{
		Il2CppChar L_14 = V_6;
		switch (((int32_t)il2cpp_codegen_subtract((int32_t)L_14, ((int32_t)114))))
		{
			case 0:
			{
				goto IL_00a5;
			}
			case 1:
			{
				goto IL_00c8;
			}
			case 2:
			{
				goto IL_00b3;
			}
			case 3:
			{
				goto IL_00be;
			}
		}
	}
	{
		goto IL_00c8;
	}

IL_0097:
	{
		// builder.Append('\n');
		StringBuilder_t* L_15 = V_0;
		NullCheck(L_15);
		StringBuilder_t* L_16;
		L_16 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_15, ((int32_t)10), NULL);
		// continue;
		goto IL_013d;
	}

IL_00a5:
	{
		// builder.Append('\r');
		StringBuilder_t* L_17 = V_0;
		NullCheck(L_17);
		StringBuilder_t* L_18;
		L_18 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_17, ((int32_t)13), NULL);
		// continue;
		goto IL_013d;
	}

IL_00b3:
	{
		// builder.Append('\t');
		StringBuilder_t* L_19 = V_0;
		NullCheck(L_19);
		StringBuilder_t* L_20;
		L_20 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_19, ((int32_t)9), NULL);
		// continue;
		goto IL_013d;
	}

IL_00be:
	{
		// builder2 = new StringBuilder();
		StringBuilder_t* L_21 = (StringBuilder_t*)il2cpp_codegen_object_new(StringBuilder_t_il2cpp_TypeInfo_var);
		NullCheck(L_21);
		StringBuilder__ctor_m1D99713357DE05DAFA296633639DB55F8C30587D(L_21, NULL);
		V_2 = L_21;
		// num = 0;
		V_3 = 0;
		// goto Label_014A;
		goto IL_0119;
	}

IL_00c8:
	{
		// if (((ch3 != '"') && (ch3 != '/')) && (ch3 != '\\'))
		Il2CppChar L_22 = V_6;
		if ((((int32_t)L_22) == ((int32_t)((int32_t)34))))
		{
			goto IL_00e8;
		}
	}
	{
		Il2CppChar L_23 = V_6;
		if ((((int32_t)L_23) == ((int32_t)((int32_t)47))))
		{
			goto IL_00e8;
		}
	}
	{
		Il2CppChar L_24 = V_6;
		if ((((int32_t)L_24) == ((int32_t)((int32_t)92))))
		{
			goto IL_00e8;
		}
	}
	{
		// if (ch3 == 'b')
		Il2CppChar L_25 = V_6;
		if ((((int32_t)L_25) == ((int32_t)((int32_t)98))))
		{
			goto IL_00f3;
		}
	}
	{
		// if (ch3 == 'f')
		Il2CppChar L_26 = V_6;
		if ((!(((uint32_t)L_26) == ((uint32_t)((int32_t)102)))))
		{
			goto IL_013d;
		}
	}
	{
		// goto Label_00F1;
		goto IL_00fd;
	}

IL_00e8:
	{
		// builder.Append(nextChar);
		StringBuilder_t* L_27 = V_0;
		Il2CppChar L_28 = V_4;
		NullCheck(L_27);
		StringBuilder_t* L_29;
		L_29 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_27, L_28, NULL);
		// continue;
		goto IL_013d;
	}

IL_00f3:
	{
		// builder.Append('\b');
		StringBuilder_t* L_30 = V_0;
		NullCheck(L_30);
		StringBuilder_t* L_31;
		L_31 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_30, 8, NULL);
		// continue;
		goto IL_013d;
	}

IL_00fd:
	{
		// builder.Append('\f');
		StringBuilder_t* L_32 = V_0;
		NullCheck(L_32);
		StringBuilder_t* L_33;
		L_33 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_32, ((int32_t)12), NULL);
		// continue;
		goto IL_013d;
	}

IL_0108:
	{
		// builder2.Append(this.NextChar);
		StringBuilder_t* L_34 = V_2;
		Il2CppChar L_35;
		L_35 = Parser_get_NextChar_m0BB5BC3A8C52E251EE99E8B3FCA5D205CF66759B(__this, NULL);
		NullCheck(L_34);
		StringBuilder_t* L_36;
		L_36 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_34, L_35, NULL);
		// num++;
		int32_t L_37 = V_3;
		V_3 = ((int32_t)il2cpp_codegen_add(L_37, 1));
	}

IL_0119:
	{
		// if (num < 4)
		int32_t L_38 = V_3;
		if ((((int32_t)L_38) < ((int32_t)4)))
		{
			goto IL_0108;
		}
	}
	{
		// builder.Append((char)Convert.ToInt32(builder2.ToString(), 0x10));
		StringBuilder_t* L_39 = V_0;
		StringBuilder_t* L_40 = V_2;
		NullCheck(L_40);
		String_t* L_41;
		L_41 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_40);
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		int32_t L_42;
		L_42 = Convert_ToInt32_mD1B3AFBDA26E52D0382434804364FEF8BA241FB4(L_41, ((int32_t)16), NULL);
		NullCheck(L_39);
		StringBuilder_t* L_43;
		L_43 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_39, ((int32_t)(uint16_t)L_42), NULL);
		// continue;
		goto IL_013d;
	}

IL_0134:
	{
		// builder.Append(nextChar);
		StringBuilder_t* L_44 = V_0;
		Il2CppChar L_45 = V_4;
		NullCheck(L_44);
		StringBuilder_t* L_46;
		L_46 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_44, L_45, NULL);
	}

IL_013d:
	{
		// while (flag)
		bool L_47 = V_1;
		if (L_47)
		{
			goto IL_0019;
		}
	}

IL_0143:
	{
		// return builder.ToString();
		StringBuilder_t* L_48 = V_0;
		NullCheck(L_48);
		String_t* L_49;
		L_49 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_48);
		return L_49;
	}
}
// System.Object RiseJson/Parser::ParseValue()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RuntimeObject* Parser_ParseValue_m30DCD24F852EDA784F16ED41833D03EB4C8D9C3F (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	int32_t V_0 = 0;
	{
		// TOKEN nextSymbol = this.NextSymbol;
		int32_t L_0;
		L_0 = Parser_get_NextSymbol_mE4BC8E813CC48B6E5CD7F6C5679AD163723A4E90(__this, NULL);
		V_0 = L_0;
		// return this.ParseBySymbol(nextSymbol);
		int32_t L_1 = V_0;
		RuntimeObject* L_2;
		L_2 = Parser_ParseBySymbol_mF0126896D2623317331073FF78E51B6933356B8B(__this, L_1, NULL);
		return L_2;
	}
}
// System.Char RiseJson/Parser::get_NextChar()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Il2CppChar Parser_get_NextChar_m0BB5BC3A8C52E251EE99E8B3FCA5D205CF66759B (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// return Convert.ToChar(this.json.Read());
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_0 = __this->___json_1;
		NullCheck(L_0);
		int32_t L_1;
		L_1 = VirtualFuncInvoker0< int32_t >::Invoke(9 /* System.Int32 System.IO.TextReader::Read() */, L_0);
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		Il2CppChar L_2;
		L_2 = Convert_ToChar_mF1B1B205DDEFDE52251235514E7DAFCAB37D1F24(L_1, NULL);
		return L_2;
	}
}
// RiseJson/Parser/TOKEN RiseJson/Parser::get_NextSymbol()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t Parser_get_NextSymbol_mE4BC8E813CC48B6E5CD7F6C5679AD163723A4E90 (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Dictionary_2_Add_m2FE98C9C3763E31D7CB55207ED3A46B33BF64883_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Dictionary_2_TryGetValue_m835BB1E6EA8A8BF1242B51E28FD65B43FEF68E2A_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Dictionary_2__ctor_mBB2DBA9ECB2AD6046CB4CFB717FDD7E474A439AB_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral5BEFD8CC60A79699B5BB00E37BAC5B62D371E174);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral77D38C0623F92B292B925F6E72CF5CF99A20D4EB);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralB7C45DD316C68ABF3429C20058C2981C652192F2);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	Il2CppChar V_1 = 0x0;
	int32_t V_2 = 0;
	{
		// if (this.json.Peek() != -1)
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_0 = __this->___json_1;
		NullCheck(L_0);
		int32_t L_1;
		L_1 = VirtualFuncInvoker0< int32_t >::Invoke(8 /* System.Int32 System.IO.TextReader::Peek() */, L_0);
		if ((((int32_t)L_1) == ((int32_t)(-1))))
		{
			goto IL_0148;
		}
	}
	{
		// this.EatWhitespace();
		Parser_EatWhitespace_m75DEE6C4273E95B5CEDC45964350830BE7C3C79C(__this, NULL);
		// switch (this.PeekChar)
		Il2CppChar L_2;
		L_2 = Parser_get_PeekChar_m896892C8D72C5D3B83B13D29DC10A043A5B0ACEC(__this, NULL);
		V_1 = L_2;
		Il2CppChar L_3 = V_1;
		if ((!(((uint32_t)L_3) <= ((uint32_t)((int32_t)91)))))
		{
			goto IL_0097;
		}
	}
	{
		Il2CppChar L_4 = V_1;
		switch (((int32_t)il2cpp_codegen_subtract((int32_t)L_4, ((int32_t)34))))
		{
			case 0:
			{
				goto IL_00a8;
			}
			case 1:
			{
				goto IL_00dc;
			}
			case 2:
			{
				goto IL_00dc;
			}
			case 3:
			{
				goto IL_00dc;
			}
			case 4:
			{
				goto IL_00dc;
			}
			case 5:
			{
				goto IL_00dc;
			}
			case 6:
			{
				goto IL_00dc;
			}
			case 7:
			{
				goto IL_00dc;
			}
			case 8:
			{
				goto IL_00dc;
			}
			case 9:
			{
				goto IL_00dc;
			}
			case 10:
			{
				goto IL_00aa;
			}
			case 11:
			{
				goto IL_00b8;
			}
			case 12:
			{
				goto IL_00dc;
			}
			case 13:
			{
				goto IL_00dc;
			}
			case 14:
			{
				goto IL_00b8;
			}
			case 15:
			{
				goto IL_00b8;
			}
			case 16:
			{
				goto IL_00b8;
			}
			case 17:
			{
				goto IL_00b8;
			}
			case 18:
			{
				goto IL_00b8;
			}
			case 19:
			{
				goto IL_00b8;
			}
			case 20:
			{
				goto IL_00b8;
			}
			case 21:
			{
				goto IL_00b8;
			}
			case 22:
			{
				goto IL_00b8;
			}
			case 23:
			{
				goto IL_00b8;
			}
			case 24:
			{
				goto IL_00ba;
			}
		}
	}
	{
		Il2CppChar L_5 = V_1;
		if ((((int32_t)L_5) == ((int32_t)((int32_t)91))))
		{
			goto IL_00bc;
		}
	}
	{
		goto IL_00dc;
	}

IL_0097:
	{
		Il2CppChar L_6 = V_1;
		if ((((int32_t)L_6) == ((int32_t)((int32_t)93))))
		{
			goto IL_00be;
		}
	}
	{
		Il2CppChar L_7 = V_1;
		if ((((int32_t)L_7) == ((int32_t)((int32_t)123))))
		{
			goto IL_00cc;
		}
	}
	{
		Il2CppChar L_8 = V_1;
		if ((((int32_t)L_8) == ((int32_t)((int32_t)125))))
		{
			goto IL_00ce;
		}
	}
	{
		goto IL_00dc;
	}

IL_00a8:
	{
		// return TOKEN.STRING;
		return (int32_t)(7);
	}

IL_00aa:
	{
		// this.json.Read();
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_9 = __this->___json_1;
		NullCheck(L_9);
		int32_t L_10;
		L_10 = VirtualFuncInvoker0< int32_t >::Invoke(9 /* System.Int32 System.IO.TextReader::Read() */, L_9);
		// return TOKEN.COMMA;
		return (int32_t)(6);
	}

IL_00b8:
	{
		// return TOKEN.NUMBER;
		return (int32_t)(8);
	}

IL_00ba:
	{
		// return TOKEN.COLON;
		return (int32_t)(5);
	}

IL_00bc:
	{
		// return TOKEN.SQUARED_OPEN;
		return (int32_t)(3);
	}

IL_00be:
	{
		// this.json.Read();
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_11 = __this->___json_1;
		NullCheck(L_11);
		int32_t L_12;
		L_12 = VirtualFuncInvoker0< int32_t >::Invoke(9 /* System.Int32 System.IO.TextReader::Read() */, L_11);
		// return TOKEN.SQUARED_CLOSE;
		return (int32_t)(4);
	}

IL_00cc:
	{
		// return TOKEN.CURLY_OPEN;
		return (int32_t)(1);
	}

IL_00ce:
	{
		// this.json.Read();
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_13 = __this->___json_1;
		NullCheck(L_13);
		int32_t L_14;
		L_14 = VirtualFuncInvoker0< int32_t >::Invoke(9 /* System.Int32 System.IO.TextReader::Read() */, L_13);
		// return TOKEN.CURLY_CLOSE;
		return (int32_t)(2);
	}

IL_00dc:
	{
		// string nextWord = this.NextWord;
		String_t* L_15;
		L_15 = Parser_get_NextWord_m01A4B42AEFAF4FD146911805717426D50F33266C(__this, NULL);
		V_0 = L_15;
		// if (nextWord != null)
		String_t* L_16 = V_0;
		if (!L_16)
		{
			goto IL_0148;
		}
	}
	{
		// if (f__switchmap0 == null)
		Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* L_17 = ((Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_StaticFields*)il2cpp_codegen_static_fields_for(Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_il2cpp_TypeInfo_var))->___f__switchmap0_0;
		if (L_17)
		{
			goto IL_011c;
		}
	}
	{
		// Dictionary<string, int> dictionary = new Dictionary<string, int>(3);
		Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* L_18 = (Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588*)il2cpp_codegen_object_new(Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588_il2cpp_TypeInfo_var);
		NullCheck(L_18);
		Dictionary_2__ctor_mBB2DBA9ECB2AD6046CB4CFB717FDD7E474A439AB(L_18, 3, Dictionary_2__ctor_mBB2DBA9ECB2AD6046CB4CFB717FDD7E474A439AB_RuntimeMethod_var);
		// dictionary.Add("false", 0);
		Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* L_19 = L_18;
		NullCheck(L_19);
		Dictionary_2_Add_m2FE98C9C3763E31D7CB55207ED3A46B33BF64883(L_19, _stringLiteral77D38C0623F92B292B925F6E72CF5CF99A20D4EB, 0, Dictionary_2_Add_m2FE98C9C3763E31D7CB55207ED3A46B33BF64883_RuntimeMethod_var);
		// dictionary.Add("true", 1);
		Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* L_20 = L_19;
		NullCheck(L_20);
		Dictionary_2_Add_m2FE98C9C3763E31D7CB55207ED3A46B33BF64883(L_20, _stringLiteralB7C45DD316C68ABF3429C20058C2981C652192F2, 1, Dictionary_2_Add_m2FE98C9C3763E31D7CB55207ED3A46B33BF64883_RuntimeMethod_var);
		// dictionary.Add("null", 2);
		Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* L_21 = L_20;
		NullCheck(L_21);
		Dictionary_2_Add_m2FE98C9C3763E31D7CB55207ED3A46B33BF64883(L_21, _stringLiteral5BEFD8CC60A79699B5BB00E37BAC5B62D371E174, 2, Dictionary_2_Add_m2FE98C9C3763E31D7CB55207ED3A46B33BF64883_RuntimeMethod_var);
		// f__switchmap0 = dictionary;
		((Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_StaticFields*)il2cpp_codegen_static_fields_for(Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_il2cpp_TypeInfo_var))->___f__switchmap0_0 = L_21;
		Il2CppCodeGenWriteBarrier((void**)(&((Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_StaticFields*)il2cpp_codegen_static_fields_for(Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_il2cpp_TypeInfo_var))->___f__switchmap0_0), (void*)L_21);
	}

IL_011c:
	{
		// if (f__switchmap0.TryGetValue(nextWord, out num))
		Dictionary_2_t5C8F46F5D57502270DD9E1DA8303B23C7FE85588* L_22 = ((Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_StaticFields*)il2cpp_codegen_static_fields_for(Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0_il2cpp_TypeInfo_var))->___f__switchmap0_0;
		String_t* L_23 = V_0;
		NullCheck(L_22);
		bool L_24;
		L_24 = Dictionary_2_TryGetValue_m835BB1E6EA8A8BF1242B51E28FD65B43FEF68E2A(L_22, L_23, (&V_2), Dictionary_2_TryGetValue_m835BB1E6EA8A8BF1242B51E28FD65B43FEF68E2A_RuntimeMethod_var);
		if (!L_24)
		{
			goto IL_0148;
		}
	}
	{
		int32_t L_25 = V_2;
		switch (L_25)
		{
			case 0:
			{
				goto IL_013f;
			}
			case 1:
			{
				goto IL_0142;
			}
			case 2:
			{
				goto IL_0145;
			}
		}
	}
	{
		goto IL_0148;
	}

IL_013f:
	{
		// return TOKEN.FALSE;
		return (int32_t)(((int32_t)10));
	}

IL_0142:
	{
		// return TOKEN.TRUE;
		return (int32_t)(((int32_t)9));
	}

IL_0145:
	{
		// return TOKEN.NULL;
		return (int32_t)(((int32_t)11));
	}

IL_0148:
	{
		// return TOKEN.NONE;
		return (int32_t)(0);
	}
}
// System.String RiseJson/Parser::get_NextWord()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Parser_get_NextWord_m01A4B42AEFAF4FD146911805717426D50F33266C (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&StringBuilder_t_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralF468E0BCDE9855E7830073A32AF7323CC7E46952);
		s_Il2CppMethodInitialized = true;
	}
	StringBuilder_t* V_0 = NULL;
	{
		// StringBuilder builder = new StringBuilder();
		StringBuilder_t* L_0 = (StringBuilder_t*)il2cpp_codegen_object_new(StringBuilder_t_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		StringBuilder__ctor_m1D99713357DE05DAFA296633639DB55F8C30587D(L_0, NULL);
		V_0 = L_0;
		goto IL_0023;
	}

IL_0008:
	{
		// builder.Append(this.NextChar);
		StringBuilder_t* L_1 = V_0;
		Il2CppChar L_2;
		L_2 = Parser_get_NextChar_m0BB5BC3A8C52E251EE99E8B3FCA5D205CF66759B(__this, NULL);
		NullCheck(L_1);
		StringBuilder_t* L_3;
		L_3 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_1, L_2, NULL);
		// if (this.json.Peek() == -1)
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_4 = __this->___json_1;
		NullCheck(L_4);
		int32_t L_5;
		L_5 = VirtualFuncInvoker0< int32_t >::Invoke(8 /* System.Int32 System.IO.TextReader::Peek() */, L_4);
		if ((((int32_t)L_5) == ((int32_t)(-1))))
		{
			goto IL_0036;
		}
	}

IL_0023:
	{
		// while (" \t\n\r{}[],:\"".IndexOf(this.PeekChar) == -1)
		Il2CppChar L_6;
		L_6 = Parser_get_PeekChar_m896892C8D72C5D3B83B13D29DC10A043A5B0ACEC(__this, NULL);
		NullCheck(_stringLiteralF468E0BCDE9855E7830073A32AF7323CC7E46952);
		int32_t L_7;
		L_7 = String_IndexOf_mE21E78F35EF4A7768E385A72814C88D22B689966(_stringLiteralF468E0BCDE9855E7830073A32AF7323CC7E46952, L_6, NULL);
		if ((((int32_t)L_7) == ((int32_t)(-1))))
		{
			goto IL_0008;
		}
	}

IL_0036:
	{
		// return builder.ToString();
		StringBuilder_t* L_8 = V_0;
		NullCheck(L_8);
		String_t* L_9;
		L_9 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_8);
		return L_9;
	}
}
// System.Char RiseJson/Parser::get_PeekChar()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR Il2CppChar Parser_get_PeekChar_m896892C8D72C5D3B83B13D29DC10A043A5B0ACEC (Parser_t8F3A1C09283088189B215B886C92464CD7E3ACC0* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// return Convert.ToChar(this.json.Peek());
		StringReader_t1A336148FF22A9584E759A9D720CC96C23E35DD8* L_0 = __this->___json_1;
		NullCheck(L_0);
		int32_t L_1;
		L_1 = VirtualFuncInvoker0< int32_t >::Invoke(8 /* System.Int32 System.IO.TextReader::Peek() */, L_0);
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		Il2CppChar L_2;
		L_2 = Convert_ToChar_mF1B1B205DDEFDE52251235514E7DAFCAB37D1F24(L_1, NULL);
		return L_2;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseJson/Serializer::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer__ctor_mAC17A0B442836479DED00AA2FAF5A86C19A090F6 (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&StringBuilder_t_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// private StringBuilder builder = new StringBuilder();
		StringBuilder_t* L_0 = (StringBuilder_t*)il2cpp_codegen_object_new(StringBuilder_t_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		StringBuilder__ctor_m1D99713357DE05DAFA296633639DB55F8C30587D(L_0, NULL);
		__this->___builder_0 = L_0;
		Il2CppCodeGenWriteBarrier((void**)(&__this->___builder_0), (void*)L_0);
		// private Serializer()
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		// }
		return;
	}
}
// System.String RiseJson/Serializer::Serialize(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR String_t* Serializer_Serialize_mC3EB9A1F672CE188D04899BD2E45C7DBDD33E2B8 (RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// RiseJson.Serializer serializer = new RiseJson.Serializer();
		Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* L_0 = (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D*)il2cpp_codegen_object_new(Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		Serializer__ctor_mAC17A0B442836479DED00AA2FAF5A86C19A090F6(L_0, NULL);
		// serializer.SerializeValue(obj);
		Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* L_1 = L_0;
		RuntimeObject* L_2 = ___0_obj;
		NullCheck(L_1);
		Serializer_SerializeValue_mDC148699C7C376EB800E67D037FD6E4DA1B38F2F(L_1, L_2, NULL);
		// return serializer.builder.ToString();
		NullCheck(L_1);
		StringBuilder_t* L_3 = L_1->___builder_0;
		NullCheck(L_3);
		String_t* L_4;
		L_4 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_3);
		return L_4;
	}
}
// System.Void RiseJson/Serializer::SerializeArray(System.Collections.IList)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeArray_m458F9C825C7E6B3234D91808251C0B24CA617F08 (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, RuntimeObject* ___0_anArray, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IEnumerable_t6331596D5DD37C462B1B8D49CF6B319B00AB7131_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IEnumerator_t7B609C2FFA6EB5167D9C62A0C32A21DE2F666DAA_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	bool V_0 = false;
	RuntimeObject* V_1 = NULL;
	RuntimeObject* V_2 = NULL;
	RuntimeObject* V_3 = NULL;
	{
		// this.builder.Append('[');
		StringBuilder_t* L_0 = __this->___builder_0;
		NullCheck(L_0);
		StringBuilder_t* L_1;
		L_1 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_0, ((int32_t)91), NULL);
		// bool flag = true;
		V_0 = (bool)1;
		// IEnumerator enumerator = anArray.GetEnumerator();
		RuntimeObject* L_2 = ___0_anArray;
		NullCheck(L_2);
		RuntimeObject* L_3;
		L_3 = InterfaceFuncInvoker0< RuntimeObject* >::Invoke(0 /* System.Collections.IEnumerator System.Collections.IEnumerable::GetEnumerator() */, IEnumerable_t6331596D5DD37C462B1B8D49CF6B319B00AB7131_il2cpp_TypeInfo_var, L_2);
		V_1 = L_3;
	}
	{
		auto __finallyBlock = il2cpp::utils::Finally([&]
		{

FINALLY_0044:
			{// begin finally (depth: 1)
				{
					// IDisposable disposable = enumerator as IDisposable;
					RuntimeObject* L_4 = V_1;
					V_3 = ((RuntimeObject*)IsInst((RuntimeObject*)L_4, IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var));
					// if (disposable != null)
					RuntimeObject* L_5 = V_3;
					if (!L_5)
					{
						goto IL_0054;
					}
				}
				{
					// disposable.Dispose();
					RuntimeObject* L_6 = V_3;
					NullCheck(L_6);
					InterfaceActionInvoker0::Invoke(0 /* System.Void System.IDisposable::Dispose() */, IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var, L_6);
				}

IL_0054:
				{
					// }
					return;
				}
			}// end finally (depth: 1)
		});
		try
		{// begin try (depth: 1)
			{
				goto IL_003a_1;
			}

IL_0019_1:
			{
				// object current = enumerator.Current;
				RuntimeObject* L_7 = V_1;
				NullCheck(L_7);
				RuntimeObject* L_8;
				L_8 = InterfaceFuncInvoker0< RuntimeObject* >::Invoke(1 /* System.Object System.Collections.IEnumerator::get_Current() */, IEnumerator_t7B609C2FFA6EB5167D9C62A0C32A21DE2F666DAA_il2cpp_TypeInfo_var, L_7);
				V_2 = L_8;
				// if (!flag)
				bool L_9 = V_0;
				if (L_9)
				{
					goto IL_0031_1;
				}
			}
			{
				// this.builder.Append(',');
				StringBuilder_t* L_10 = __this->___builder_0;
				NullCheck(L_10);
				StringBuilder_t* L_11;
				L_11 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_10, ((int32_t)44), NULL);
			}

IL_0031_1:
			{
				// this.SerializeValue(current);
				RuntimeObject* L_12 = V_2;
				Serializer_SerializeValue_mDC148699C7C376EB800E67D037FD6E4DA1B38F2F(__this, L_12, NULL);
				// flag = false;
				V_0 = (bool)0;
			}

IL_003a_1:
			{
				// while (enumerator.MoveNext())
				RuntimeObject* L_13 = V_1;
				NullCheck(L_13);
				bool L_14;
				L_14 = InterfaceFuncInvoker0< bool >::Invoke(0 /* System.Boolean System.Collections.IEnumerator::MoveNext() */, IEnumerator_t7B609C2FFA6EB5167D9C62A0C32A21DE2F666DAA_il2cpp_TypeInfo_var, L_13);
				if (L_14)
				{
					goto IL_0019_1;
				}
			}
			{
				// }
				goto IL_0055;
			}
		}// end try (depth: 1)
		catch(Il2CppExceptionWrapper& e)
		{
			__finallyBlock.StoreException(e.ex);
		}
	}

IL_0055:
	{
		// this.builder.Append(']');
		StringBuilder_t* L_15 = __this->___builder_0;
		NullCheck(L_15);
		StringBuilder_t* L_16;
		L_16 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_15, ((int32_t)93), NULL);
		// }
		return;
	}
}
// System.Void RiseJson/Serializer::SerializeObject(System.Collections.IDictionary)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeObject_mBE4BB9A6C82762643C1B4B409B3855A838A1895F (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IDictionary_t6D03155AF1FA9083817AA5B6AD7DEEACC26AB220_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IEnumerable_t6331596D5DD37C462B1B8D49CF6B319B00AB7131_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IEnumerator_t7B609C2FFA6EB5167D9C62A0C32A21DE2F666DAA_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	bool V_0 = false;
	RuntimeObject* V_1 = NULL;
	RuntimeObject* V_2 = NULL;
	RuntimeObject* V_3 = NULL;
	{
		// bool flag = true;
		V_0 = (bool)1;
		// this.builder.Append('{');
		StringBuilder_t* L_0 = __this->___builder_0;
		NullCheck(L_0);
		StringBuilder_t* L_1;
		L_1 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_0, ((int32_t)123), NULL);
		// IEnumerator enumerator = obj.Keys.GetEnumerator();
		RuntimeObject* L_2 = ___0_obj;
		NullCheck(L_2);
		RuntimeObject* L_3;
		L_3 = InterfaceFuncInvoker0< RuntimeObject* >::Invoke(2 /* System.Collections.ICollection System.Collections.IDictionary::get_Keys() */, IDictionary_t6D03155AF1FA9083817AA5B6AD7DEEACC26AB220_il2cpp_TypeInfo_var, L_2);
		NullCheck(L_3);
		RuntimeObject* L_4;
		L_4 = InterfaceFuncInvoker0< RuntimeObject* >::Invoke(0 /* System.Collections.IEnumerator System.Collections.IEnumerable::GetEnumerator() */, IEnumerable_t6331596D5DD37C462B1B8D49CF6B319B00AB7131_il2cpp_TypeInfo_var, L_3);
		V_1 = L_4;
	}
	{
		auto __finallyBlock = il2cpp::utils::Finally([&]
		{

FINALLY_0069:
			{// begin finally (depth: 1)
				{
					// IDisposable disposable = enumerator as IDisposable;
					RuntimeObject* L_5 = V_1;
					V_3 = ((RuntimeObject*)IsInst((RuntimeObject*)L_5, IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var));
					// if (disposable != null)
					RuntimeObject* L_6 = V_3;
					if (!L_6)
					{
						goto IL_0079;
					}
				}
				{
					// disposable.Dispose();
					RuntimeObject* L_7 = V_3;
					NullCheck(L_7);
					InterfaceActionInvoker0::Invoke(0 /* System.Void System.IDisposable::Dispose() */, IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var, L_7);
				}

IL_0079:
				{
					// }
					return;
				}
			}// end finally (depth: 1)
		});
		try
		{// begin try (depth: 1)
			{
				goto IL_005f_1;
			}

IL_001e_1:
			{
				// object current = enumerator.Current;
				RuntimeObject* L_8 = V_1;
				NullCheck(L_8);
				RuntimeObject* L_9;
				L_9 = InterfaceFuncInvoker0< RuntimeObject* >::Invoke(1 /* System.Object System.Collections.IEnumerator::get_Current() */, IEnumerator_t7B609C2FFA6EB5167D9C62A0C32A21DE2F666DAA_il2cpp_TypeInfo_var, L_8);
				V_2 = L_9;
				// if (!flag)
				bool L_10 = V_0;
				if (L_10)
				{
					goto IL_0036_1;
				}
			}
			{
				// this.builder.Append(',');
				StringBuilder_t* L_11 = __this->___builder_0;
				NullCheck(L_11);
				StringBuilder_t* L_12;
				L_12 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_11, ((int32_t)44), NULL);
			}

IL_0036_1:
			{
				// this.SerializeString(current.ToString());
				RuntimeObject* L_13 = V_2;
				NullCheck(L_13);
				String_t* L_14;
				L_14 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_13);
				Serializer_SerializeString_m0F09C490477C3813721AFCAE1E9479F60FCA86DB(__this, L_14, NULL);
				// this.builder.Append(':');
				StringBuilder_t* L_15 = __this->___builder_0;
				NullCheck(L_15);
				StringBuilder_t* L_16;
				L_16 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_15, ((int32_t)58), NULL);
				// this.SerializeValue(obj[current]);
				RuntimeObject* L_17 = ___0_obj;
				RuntimeObject* L_18 = V_2;
				NullCheck(L_17);
				RuntimeObject* L_19;
				L_19 = InterfaceFuncInvoker1< RuntimeObject*, RuntimeObject* >::Invoke(0 /* System.Object System.Collections.IDictionary::get_Item(System.Object) */, IDictionary_t6D03155AF1FA9083817AA5B6AD7DEEACC26AB220_il2cpp_TypeInfo_var, L_17, L_18);
				Serializer_SerializeValue_mDC148699C7C376EB800E67D037FD6E4DA1B38F2F(__this, L_19, NULL);
				// flag = false;
				V_0 = (bool)0;
			}

IL_005f_1:
			{
				// while (enumerator.MoveNext())
				RuntimeObject* L_20 = V_1;
				NullCheck(L_20);
				bool L_21;
				L_21 = InterfaceFuncInvoker0< bool >::Invoke(0 /* System.Boolean System.Collections.IEnumerator::MoveNext() */, IEnumerator_t7B609C2FFA6EB5167D9C62A0C32A21DE2F666DAA_il2cpp_TypeInfo_var, L_20);
				if (L_21)
				{
					goto IL_001e_1;
				}
			}
			{
				// }
				goto IL_007a;
			}
		}// end try (depth: 1)
		catch(Il2CppExceptionWrapper& e)
		{
			__finallyBlock.StoreException(e.ex);
		}
	}

IL_007a:
	{
		// this.builder.Append('}');
		StringBuilder_t* L_22 = __this->___builder_0;
		NullCheck(L_22);
		StringBuilder_t* L_23;
		L_23 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_22, ((int32_t)125), NULL);
		// }
		return;
	}
}
// System.Void RiseJson/Serializer::SerializeOther(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeOther_m074A35B91C2E7045976EA6FF97098BE737FF3584 (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, RuntimeObject* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Byte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Double_tE150EF3D1D43DEE85D533810AB4C742307EEDE5F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Int16_tB8EF286A9C33492FA6E6D6E67320BE93E794A175_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Int64_t092CFB123BE63C28ACDAF65C68F21A526050DBA3_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&SByte_tFEFFEF5D2FEBF5207950AE6FAC150FC53B668DB5_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Single_t4530F2FF86FCB0DC29F35385CA1BD21BE294761C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&UInt16_tF4C148C876015C212FD72652D0B6ED8CC247A455_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&UInt32_t1833D51FFA667B18A5AA4B8D34DE284F8495D29B_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&UInt64_t8F12534CC8FC4B5860F2A2CD1EE79D322E7A41AF_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if ((((value is float) || (value is int)) || ((value is uint) || (value is long))) || ((((value is double) || (value is sbyte)) || ((value is byte) || (value is short))) || (((value is ushort) || (value is ulong)) || (value is decimal))))
		RuntimeObject* L_0 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_0, Single_t4530F2FF86FCB0DC29F35385CA1BD21BE294761C_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_1 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_1, Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_2 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_2, UInt32_t1833D51FFA667B18A5AA4B8D34DE284F8495D29B_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_3 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_3, Int64_t092CFB123BE63C28ACDAF65C68F21A526050DBA3_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_4 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_4, Double_tE150EF3D1D43DEE85D533810AB4C742307EEDE5F_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_5 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_5, SByte_tFEFFEF5D2FEBF5207950AE6FAC150FC53B668DB5_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_6 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_6, Byte_t94D9231AC217BE4D2E004C4CD32DF6D099EA41A3_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_7 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_7, Int16_tB8EF286A9C33492FA6E6D6E67320BE93E794A175_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_8 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_8, UInt16_tF4C148C876015C212FD72652D0B6ED8CC247A455_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_9 = ___0_value;
		if (((RuntimeObject*)IsInstSealed((RuntimeObject*)L_9, UInt64_t8F12534CC8FC4B5860F2A2CD1EE79D322E7A41AF_il2cpp_TypeInfo_var)))
		{
			goto IL_0058;
		}
	}
	{
		RuntimeObject* L_10 = ___0_value;
		if (!((RuntimeObject*)IsInstSealed((RuntimeObject*)L_10, Decimal_tDA6C877282B2D789CF97C0949661CC11D643969F_il2cpp_TypeInfo_var)))
		{
			goto IL_006b;
		}
	}

IL_0058:
	{
		// this.builder.Append(value.ToString());
		StringBuilder_t* L_11 = __this->___builder_0;
		RuntimeObject* L_12 = ___0_value;
		NullCheck(L_12);
		String_t* L_13;
		L_13 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_12);
		NullCheck(L_11);
		StringBuilder_t* L_14;
		L_14 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_11, L_13, NULL);
		return;
	}

IL_006b:
	{
		// this.SerializeString(value.ToString());
		RuntimeObject* L_15 = ___0_value;
		NullCheck(L_15);
		String_t* L_16;
		L_16 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_15);
		Serializer_SerializeString_m0F09C490477C3813721AFCAE1E9479F60FCA86DB(__this, L_16, NULL);
		// }
		return;
	}
}
// System.Void RiseJson/Serializer::SerializeString(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeString_m0F09C490477C3813721AFCAE1E9479F60FCA86DB (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, String_t* ___0_str, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral5962E944D7340CE47999BF097B4AFD70C1501FB9);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral785F17F45C331C415D0A7458E6AAC36966399C51);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral7F3238CD8C342B06FB9AB185C610175C84625462);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral848E5ED630B3142F565DD995C6E8D30187ED33CD);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralA7C3FCA8C63E127B542B38A5CA5E3FEEDDD1B122);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralB78F235D4291950A7D101307609C259F3E1F033F);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA666908BB15F4E1D2649752EC5DCBD0D5C64699);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralF18840F490E42D3CE48CDCBF47229C1C240F8ABE);
		s_Il2CppMethodInitialized = true;
	}
	CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* V_0 = NULL;
	int32_t V_1 = 0;
	Il2CppChar V_2 = 0x0;
	int32_t V_3 = 0;
	{
		// this.builder.Append('"');
		StringBuilder_t* L_0 = __this->___builder_0;
		NullCheck(L_0);
		StringBuilder_t* L_1;
		L_1 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_0, ((int32_t)34), NULL);
		// foreach (char ch in str.ToCharArray())
		String_t* L_2 = ___0_str;
		NullCheck(L_2);
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_3;
		L_3 = String_ToCharArray_m0699A92AA3E744229EF29CB9D943C47DF4FE5B46(L_2, NULL);
		V_0 = L_3;
		V_1 = 0;
		goto IL_012a;
	}

IL_001c:
	{
		// foreach (char ch in str.ToCharArray())
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_4 = V_0;
		int32_t L_5 = V_1;
		NullCheck(L_4);
		int32_t L_6 = L_5;
		uint16_t L_7 = (uint16_t)(L_4)->GetAt(static_cast<il2cpp_array_size_t>(L_6));
		V_2 = L_7;
		Il2CppChar L_8 = V_2;
		switch (((int32_t)il2cpp_codegen_subtract((int32_t)L_8, 8)))
		{
			case 0:
			{
				goto IL_0052;
			}
			case 1:
			{
				goto IL_0068;
			}
			case 2:
			{
				goto IL_007e;
			}
			case 3:
			{
				goto IL_00e0;
			}
			case 4:
			{
				goto IL_0094;
			}
			case 5:
			{
				goto IL_00a7;
			}
		}
	}
	{
		Il2CppChar L_9 = V_2;
		if ((((int32_t)L_9) == ((int32_t)((int32_t)34))))
		{
			goto IL_00ba;
		}
	}
	{
		Il2CppChar L_10 = V_2;
		if ((((int32_t)L_10) == ((int32_t)((int32_t)92))))
		{
			goto IL_00cd;
		}
	}
	{
		goto IL_00e0;
	}

IL_0052:
	{
		// this.builder.Append(@"\b");
		StringBuilder_t* L_11 = __this->___builder_0;
		NullCheck(L_11);
		StringBuilder_t* L_12;
		L_12 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_11, _stringLiteral5962E944D7340CE47999BF097B4AFD70C1501FB9, NULL);
		// break;
		goto IL_0126;
	}

IL_0068:
	{
		// this.builder.Append(@"\t");
		StringBuilder_t* L_13 = __this->___builder_0;
		NullCheck(L_13);
		StringBuilder_t* L_14;
		L_14 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_13, _stringLiteral7F3238CD8C342B06FB9AB185C610175C84625462, NULL);
		// break;
		goto IL_0126;
	}

IL_007e:
	{
		// this.builder.Append(@"\n");
		StringBuilder_t* L_15 = __this->___builder_0;
		NullCheck(L_15);
		StringBuilder_t* L_16;
		L_16 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_15, _stringLiteral785F17F45C331C415D0A7458E6AAC36966399C51, NULL);
		// break;
		goto IL_0126;
	}

IL_0094:
	{
		// this.builder.Append(@"\f");
		StringBuilder_t* L_17 = __this->___builder_0;
		NullCheck(L_17);
		StringBuilder_t* L_18;
		L_18 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_17, _stringLiteralA7C3FCA8C63E127B542B38A5CA5E3FEEDDD1B122, NULL);
		// break;
		goto IL_0126;
	}

IL_00a7:
	{
		// this.builder.Append(@"\r");
		StringBuilder_t* L_19 = __this->___builder_0;
		NullCheck(L_19);
		StringBuilder_t* L_20;
		L_20 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_19, _stringLiteralB78F235D4291950A7D101307609C259F3E1F033F, NULL);
		// break;
		goto IL_0126;
	}

IL_00ba:
	{
		// this.builder.Append("\\\"");
		StringBuilder_t* L_21 = __this->___builder_0;
		NullCheck(L_21);
		StringBuilder_t* L_22;
		L_22 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_21, _stringLiteral848E5ED630B3142F565DD995C6E8D30187ED33CD, NULL);
		// break;
		goto IL_0126;
	}

IL_00cd:
	{
		// this.builder.Append(@"\\");
		StringBuilder_t* L_23 = __this->___builder_0;
		NullCheck(L_23);
		StringBuilder_t* L_24;
		L_24 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_23, _stringLiteralF18840F490E42D3CE48CDCBF47229C1C240F8ABE, NULL);
		// break;
		goto IL_0126;
	}

IL_00e0:
	{
		// int num2 = Convert.ToInt32(ch);
		Il2CppChar L_25 = V_2;
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		int32_t L_26;
		L_26 = Convert_ToInt32_mDBBE9318A7CCE1560974CE93F5BFED9931CF0052(L_25, NULL);
		V_3 = L_26;
		// if ((num2 >= 0x20) && (num2 <= 0x7e))
		int32_t L_27 = V_3;
		if ((((int32_t)L_27) < ((int32_t)((int32_t)32))))
		{
			goto IL_0100;
		}
	}
	{
		int32_t L_28 = V_3;
		if ((((int32_t)L_28) > ((int32_t)((int32_t)126))))
		{
			goto IL_0100;
		}
	}
	{
		// this.builder.Append(ch);
		StringBuilder_t* L_29 = __this->___builder_0;
		Il2CppChar L_30 = V_2;
		NullCheck(L_29);
		StringBuilder_t* L_31;
		L_31 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_29, L_30, NULL);
		goto IL_0126;
	}

IL_0100:
	{
		// this.builder.Append(@"\u" + Convert.ToString(num2, 0x10).PadLeft(4, '0'));
		StringBuilder_t* L_32 = __this->___builder_0;
		int32_t L_33 = V_3;
		il2cpp_codegen_runtime_class_init_inline(Convert_t7097FF336D592F7C06D88A98349A44646F91EFFC_il2cpp_TypeInfo_var);
		String_t* L_34;
		L_34 = Convert_ToString_mC3349029FE37EB00B5BFCB1F87022458A3834E35(L_33, ((int32_t)16), NULL);
		NullCheck(L_34);
		String_t* L_35;
		L_35 = String_PadLeft_m99DDD242908E78B71E9631EE66331E8A130EB31F(L_34, 4, ((int32_t)48), NULL);
		String_t* L_36;
		L_36 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(_stringLiteralDA666908BB15F4E1D2649752EC5DCBD0D5C64699, L_35, NULL);
		NullCheck(L_32);
		StringBuilder_t* L_37;
		L_37 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_32, L_36, NULL);
	}

IL_0126:
	{
		int32_t L_38 = V_1;
		V_1 = ((int32_t)il2cpp_codegen_add(L_38, 1));
	}

IL_012a:
	{
		// foreach (char ch in str.ToCharArray())
		int32_t L_39 = V_1;
		CharU5BU5D_t799905CF001DD5F13F7DBB310181FC4D8B7D0AAB* L_40 = V_0;
		NullCheck(L_40);
		if ((((int32_t)L_39) < ((int32_t)((int32_t)(((RuntimeArray*)L_40)->max_length)))))
		{
			goto IL_001c;
		}
	}
	{
		// this.builder.Append('"');
		StringBuilder_t* L_41 = __this->___builder_0;
		NullCheck(L_41);
		StringBuilder_t* L_42;
		L_42 = StringBuilder_Append_m71228B30F05724CD2CD96D9611DCD61BFB96A6E1(L_41, ((int32_t)34), NULL);
		// }
		return;
	}
}
// System.Void RiseJson/Serializer::SerializeValue(System.Object)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void Serializer_SerializeValue_mDC148699C7C376EB800E67D037FD6E4DA1B38F2F (Serializer_tA2FB2022AF047FC3F13FC104199C9FE0746E5F3D* __this, RuntimeObject* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Char_t521A6F19B456D956AF452D926C32709DC03D6B17_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IDictionary_t6D03155AF1FA9083817AA5B6AD7DEEACC26AB220_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IList_t1C522956D79B7DC92B5B01053DF1AC058C8B598D_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&String_t_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral5BEFD8CC60A79699B5BB00E37BAC5B62D371E174);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	RuntimeObject* V_1 = NULL;
	RuntimeObject* V_2 = NULL;
	{
		// if (value == null)
		RuntimeObject* L_0 = ___0_value;
		if (L_0)
		{
			goto IL_0015;
		}
	}
	{
		// this.builder.Append("null");
		StringBuilder_t* L_1 = __this->___builder_0;
		NullCheck(L_1);
		StringBuilder_t* L_2;
		L_2 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_1, _stringLiteral5BEFD8CC60A79699B5BB00E37BAC5B62D371E174, NULL);
		return;
	}

IL_0015:
	{
		// string str = value as string;
		RuntimeObject* L_3 = ___0_value;
		V_0 = ((String_t*)IsInstSealed((RuntimeObject*)L_3, String_t_il2cpp_TypeInfo_var));
		// if (str != null)
		String_t* L_4 = V_0;
		if (!L_4)
		{
			goto IL_0027;
		}
	}
	{
		// this.SerializeString(str);
		String_t* L_5 = V_0;
		Serializer_SerializeString_m0F09C490477C3813721AFCAE1E9479F60FCA86DB(__this, L_5, NULL);
		return;
	}

IL_0027:
	{
		// else if (value is bool)
		RuntimeObject* L_6 = ___0_value;
		if (!((RuntimeObject*)IsInstSealed((RuntimeObject*)L_6, Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var)))
		{
			goto IL_0047;
		}
	}
	{
		// this.builder.Append(value.ToString().ToLower());
		StringBuilder_t* L_7 = __this->___builder_0;
		RuntimeObject* L_8 = ___0_value;
		NullCheck(L_8);
		String_t* L_9;
		L_9 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_8);
		NullCheck(L_9);
		String_t* L_10;
		L_10 = String_ToLower_m6191ABA3DC514ED47C10BDA23FD0DDCEAE7ACFBD(L_9, NULL);
		NullCheck(L_7);
		StringBuilder_t* L_11;
		L_11 = StringBuilder_Append_m08904D74E0C78E5F36DCD9C9303BDD07886D9F7D(L_7, L_10, NULL);
		return;
	}

IL_0047:
	{
		// IList anArray = value as IList;
		RuntimeObject* L_12 = ___0_value;
		V_1 = ((RuntimeObject*)IsInst((RuntimeObject*)L_12, IList_t1C522956D79B7DC92B5B01053DF1AC058C8B598D_il2cpp_TypeInfo_var));
		// if (anArray != null)
		RuntimeObject* L_13 = V_1;
		if (!L_13)
		{
			goto IL_0059;
		}
	}
	{
		// this.SerializeArray(anArray);
		RuntimeObject* L_14 = V_1;
		Serializer_SerializeArray_m458F9C825C7E6B3234D91808251C0B24CA617F08(__this, L_14, NULL);
		return;
	}

IL_0059:
	{
		// IDictionary dictionary = value as IDictionary;
		RuntimeObject* L_15 = ___0_value;
		V_2 = ((RuntimeObject*)IsInst((RuntimeObject*)L_15, IDictionary_t6D03155AF1FA9083817AA5B6AD7DEEACC26AB220_il2cpp_TypeInfo_var));
		// if (dictionary != null)
		RuntimeObject* L_16 = V_2;
		if (!L_16)
		{
			goto IL_006b;
		}
	}
	{
		// this.SerializeObject(dictionary);
		RuntimeObject* L_17 = V_2;
		Serializer_SerializeObject_mBE4BB9A6C82762643C1B4B409B3855A838A1895F(__this, L_17, NULL);
		return;
	}

IL_006b:
	{
		// else if (value is char)
		RuntimeObject* L_18 = ___0_value;
		if (!((RuntimeObject*)IsInstSealed((RuntimeObject*)L_18, Char_t521A6F19B456D956AF452D926C32709DC03D6B17_il2cpp_TypeInfo_var)))
		{
			goto IL_0080;
		}
	}
	{
		// this.SerializeString(value.ToString());
		RuntimeObject* L_19 = ___0_value;
		NullCheck(L_19);
		String_t* L_20;
		L_20 = VirtualFuncInvoker0< String_t* >::Invoke(3 /* System.String System.Object::ToString() */, L_19);
		Serializer_SerializeString_m0F09C490477C3813721AFCAE1E9479F60FCA86DB(__this, L_20, NULL);
		return;
	}

IL_0080:
	{
		// this.SerializeOther(value);
		RuntimeObject* L_21 = ___0_value;
		Serializer_SerializeOther_m074A35B91C2E7045976EA6FF97098BE737FF3584(__this, L_21, NULL);
		// }
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.IRiseSdk::CallSafeOnMainThread(System.Action)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57 (IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* __this, Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* ___0_action, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass13_0_U3CCallSafeOnMainThreadU3Eb__0_mEDBBC07F399DCFF0ABF83639ACF8D9D17B04D5FD_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral4D613657609485AE586A3379BA0E3FC13C1E1078);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral7D5D6BBF8281151C9F5F57DE5D5BABB7140A651D);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralFB4AE4F77150C3A8E8E4F8B23E734E0C7277B7D9);
		s_Il2CppMethodInitialized = true;
	}
	U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C* V_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* V_1 = NULL;
	AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* V_2 = NULL;
	Exception_t* V_3 = NULL;
	il2cpp::utils::ExceptionSupportStack<RuntimeObject*, 1> __active_exceptions;
	{
		U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C* L_0 = (U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C*)il2cpp_codegen_object_new(U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		U3CU3Ec__DisplayClass13_0__ctor_m38F954BB4142E740723017A0FD28BB04FAF9735E(L_0, NULL);
		V_0 = L_0;
		U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C* L_1 = V_0;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_2 = ___0_action;
		NullCheck(L_1);
		L_1->___action_0 = L_2;
		Il2CppCodeGenWriteBarrier((void**)(&L_1->___action_0), (void*)L_2);
	}
	try
	{// begin try (depth: 1)
		{
			// if (Application.platform == RuntimePlatform.Android)
			int32_t L_3;
			L_3 = Application_get_platform_m59EF7D6155D18891B24767F83F388160B1FF2138(NULL);
			if ((!(((uint32_t)L_3) == ((uint32_t)((int32_t)11)))))
			{
				goto IL_0065_1;
			}
		}
		{
			// using (AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
			AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_4 = (AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03*)il2cpp_codegen_object_new(AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var);
			NullCheck(L_4);
			AndroidJavaClass__ctor_mB5466169E1151B8CC44C8FED234D79984B431389(L_4, _stringLiteral4D613657609485AE586A3379BA0E3FC13C1E1078, NULL);
			V_1 = L_4;
		}
		{
			auto __finallyBlock = il2cpp::utils::Finally([&]
			{

FINALLY_005b_1:
				{// begin finally (depth: 2)
					{
						AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_5 = V_1;
						if (!L_5)
						{
							goto IL_0064_1;
						}
					}
					{
						AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_6 = V_1;
						NullCheck(L_6);
						InterfaceActionInvoker0::Invoke(0 /* System.Void System.IDisposable::Dispose() */, IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var, L_6);
					}

IL_0064_1:
					{
						return;
					}
				}// end finally (depth: 2)
			});
			try
			{// begin try (depth: 2)
				{
					// using (AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity"))
					AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_7 = V_1;
					NullCheck(L_7);
					AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_8;
					L_8 = AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD(L_7, _stringLiteralFB4AE4F77150C3A8E8E4F8B23E734E0C7277B7D9, AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD_RuntimeMethod_var);
					V_2 = L_8;
				}
				{
					auto __finallyBlock = il2cpp::utils::Finally([&]
					{

FINALLY_004f_2:
						{// begin finally (depth: 3)
							{
								AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_9 = V_2;
								if (!L_9)
								{
									goto IL_0058_2;
								}
							}
							{
								AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_10 = V_2;
								NullCheck(L_10);
								InterfaceActionInvoker0::Invoke(0 /* System.Void System.IDisposable::Dispose() */, IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var, L_10);
							}

IL_0058_2:
							{
								return;
							}
						}// end finally (depth: 3)
					});
					try
					{// begin try (depth: 3)
						// activity.Call("runOnUiThread", new AndroidJavaRunnable(() =>
						// {
						//     action?.Invoke();
						// }));
						AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_11 = V_2;
						ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_12 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)1);
						ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_13 = L_12;
						U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C* L_14 = V_0;
						AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F* L_15 = (AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F*)il2cpp_codegen_object_new(AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F_il2cpp_TypeInfo_var);
						NullCheck(L_15);
						AndroidJavaRunnable__ctor_m000E4FEB2DE8031A1CD733610D76E2BF60490334(L_15, L_14, (intptr_t)((void*)U3CU3Ec__DisplayClass13_0_U3CCallSafeOnMainThreadU3Eb__0_mEDBBC07F399DCFF0ABF83639ACF8D9D17B04D5FD_RuntimeMethod_var), NULL);
						NullCheck(L_13);
						ArrayElementTypeCheck (L_13, L_15);
						(L_13)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_15);
						NullCheck(L_11);
						AndroidJavaObject_Call_mDEF7846E2AB1C5379069BB21049ED55A9D837B1C(L_11, _stringLiteral7D5D6BBF8281151C9F5F57DE5D5BABB7140A651D, L_13, NULL);
						// }
						goto IL_0059_2;
					}// end try (depth: 3)
					catch(Il2CppExceptionWrapper& e)
					{
						__finallyBlock.StoreException(e.ex);
					}
				}

IL_0059_2:
				{
					// }
					goto IL_0065_1;
				}
			}// end try (depth: 2)
			catch(Il2CppExceptionWrapper& e)
			{
				__finallyBlock.StoreException(e.ex);
			}
		}

IL_0065_1:
		{
			// }
			goto IL_008a;
		}
	}// end try (depth: 1)
	catch(Il2CppExceptionWrapper& e)
	{
		if(il2cpp_codegen_class_is_assignable_from (((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&Exception_t_il2cpp_TypeInfo_var)), il2cpp_codegen_object_class(e.ex)))
		{
			IL2CPP_PUSH_ACTIVE_EXCEPTION(e.ex);
			goto CATCH_0067;
		}
		throw e;
	}

CATCH_0067:
	{// begin catch(System.Exception)
		// catch (Exception e)
		V_3 = ((Exception_t*)IL2CPP_GET_ACTIVE_EXCEPTION(Exception_t*));
		// Debug.LogError("RiseSdk Init Error:::\n" + e.StackTrace + "\n" + e.Message);
		Exception_t* L_16 = V_3;
		NullCheck(L_16);
		String_t* L_17;
		L_17 = VirtualFuncInvoker0< String_t* >::Invoke(8 /* System.String System.Exception::get_StackTrace() */, L_16);
		Exception_t* L_18 = V_3;
		NullCheck(L_18);
		String_t* L_19;
		L_19 = VirtualFuncInvoker0< String_t* >::Invoke(5 /* System.String System.Exception::get_Message() */, L_18);
		String_t* L_20;
		L_20 = String_Concat_m093934F71A9B351911EE46311674ED463B180006(((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral109B9483299C123690E4FB1FBE4442731D158EB5)), L_17, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral00B28FF06B788B9B67C6B259800F404F9F3761FD)), L_19, NULL);
		il2cpp_codegen_runtime_class_init_inline(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var)));
		Debug_LogError_mB00B2B4468EF3CAF041B038D840820FB84C924B2(L_20, NULL);
		// }
		IL2CPP_POP_ACTIVE_EXCEPTION();
		goto IL_008a;
	}// end catch (depth: 1)

IL_008a:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.IRiseSdk::CallSafeOnMainThread2(System.Action`1<UnityEngine.AndroidJavaObject>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void IRiseSdk_CallSafeOnMainThread2_mC76A4339B51170DA0BE042688ED53D5F3EBCEDAA (IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* __this, Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* ___0_action, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass14_0_U3CCallSafeOnMainThread2U3Eb__0_m904DAFB6EB35FCAE052734C5BEB981562E3789CB_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral4D613657609485AE586A3379BA0E3FC13C1E1078);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral7D5D6BBF8281151C9F5F57DE5D5BABB7140A651D);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralFB4AE4F77150C3A8E8E4F8B23E734E0C7277B7D9);
		s_Il2CppMethodInitialized = true;
	}
	U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* V_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* V_1 = NULL;
	Exception_t* V_2 = NULL;
	il2cpp::utils::ExceptionSupportStack<RuntimeObject*, 1> __active_exceptions;
	{
		U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* L_0 = (U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B*)il2cpp_codegen_object_new(U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		U3CU3Ec__DisplayClass14_0__ctor_m11BC8FD83CD5D255A3E6471C016D2506EEABDE42(L_0, NULL);
		V_0 = L_0;
		U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* L_1 = V_0;
		Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* L_2 = ___0_action;
		NullCheck(L_1);
		L_1->___action_0 = L_2;
		Il2CppCodeGenWriteBarrier((void**)(&L_1->___action_0), (void*)L_2);
	}
	try
	{// begin try (depth: 1)
		{
			// if (Application.platform == RuntimePlatform.Android)
			int32_t L_3;
			L_3 = Application_get_platform_m59EF7D6155D18891B24767F83F388160B1FF2138(NULL);
			if ((!(((uint32_t)L_3) == ((uint32_t)((int32_t)11)))))
			{
				goto IL_0079_1;
			}
		}
		{
			// using (AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
			AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_4 = (AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03*)il2cpp_codegen_object_new(AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var);
			NullCheck(L_4);
			AndroidJavaClass__ctor_mB5466169E1151B8CC44C8FED234D79984B431389(L_4, _stringLiteral4D613657609485AE586A3379BA0E3FC13C1E1078, NULL);
			V_1 = L_4;
		}
		{
			auto __finallyBlock = il2cpp::utils::Finally([&]
			{

FINALLY_006f_1:
				{// begin finally (depth: 2)
					{
						AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_5 = V_1;
						if (!L_5)
						{
							goto IL_0078_1;
						}
					}
					{
						AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_6 = V_1;
						NullCheck(L_6);
						InterfaceActionInvoker0::Invoke(0 /* System.Void System.IDisposable::Dispose() */, IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var, L_6);
					}

IL_0078_1:
					{
						return;
					}
				}// end finally (depth: 2)
			});
			try
			{// begin try (depth: 2)
				{
					// using (AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity"))
					U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* L_7 = V_0;
					AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_8 = V_1;
					NullCheck(L_8);
					AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_9;
					L_9 = AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD(L_8, _stringLiteralFB4AE4F77150C3A8E8E4F8B23E734E0C7277B7D9, AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD_RuntimeMethod_var);
					NullCheck(L_7);
					L_7->___activity_1 = L_9;
					Il2CppCodeGenWriteBarrier((void**)(&L_7->___activity_1), (void*)L_9);
				}
				{
					auto __finallyBlock = il2cpp::utils::Finally([&]
					{

FINALLY_0059_2:
						{// begin finally (depth: 3)
							{
								U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* L_10 = V_0;
								NullCheck(L_10);
								AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_11 = L_10->___activity_1;
								if (!L_11)
								{
									goto IL_006c_2;
								}
							}
							{
								U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* L_12 = V_0;
								NullCheck(L_12);
								AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_13 = L_12->___activity_1;
								NullCheck(L_13);
								InterfaceActionInvoker0::Invoke(0 /* System.Void System.IDisposable::Dispose() */, IDisposable_t030E0496B4E0E4E4F086825007979AF51F7248C5_il2cpp_TypeInfo_var, L_13);
							}

IL_006c_2:
							{
								return;
							}
						}// end finally (depth: 3)
					});
					try
					{// begin try (depth: 3)
						// activity.Call("runOnUiThread", new AndroidJavaRunnable(() =>
						// {
						//     action?.Invoke(activity);
						// }));
						U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* L_14 = V_0;
						NullCheck(L_14);
						AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_15 = L_14->___activity_1;
						ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_16 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)1);
						ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_17 = L_16;
						U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* L_18 = V_0;
						AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F* L_19 = (AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F*)il2cpp_codegen_object_new(AndroidJavaRunnable_tF23B9BBDA8C99A48BCEEA6335A47DA3C0EF34A7F_il2cpp_TypeInfo_var);
						NullCheck(L_19);
						AndroidJavaRunnable__ctor_m000E4FEB2DE8031A1CD733610D76E2BF60490334(L_19, L_18, (intptr_t)((void*)U3CU3Ec__DisplayClass14_0_U3CCallSafeOnMainThread2U3Eb__0_m904DAFB6EB35FCAE052734C5BEB981562E3789CB_RuntimeMethod_var), NULL);
						NullCheck(L_17);
						ArrayElementTypeCheck (L_17, L_19);
						(L_17)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_19);
						NullCheck(L_15);
						AndroidJavaObject_Call_mDEF7846E2AB1C5379069BB21049ED55A9D837B1C(L_15, _stringLiteral7D5D6BBF8281151C9F5F57DE5D5BABB7140A651D, L_17, NULL);
						// }
						goto IL_006d_2;
					}// end try (depth: 3)
					catch(Il2CppExceptionWrapper& e)
					{
						__finallyBlock.StoreException(e.ex);
					}
				}

IL_006d_2:
				{
					// }
					goto IL_0079_1;
				}
			}// end try (depth: 2)
			catch(Il2CppExceptionWrapper& e)
			{
				__finallyBlock.StoreException(e.ex);
			}
		}

IL_0079_1:
		{
			// }
			goto IL_009e;
		}
	}// end try (depth: 1)
	catch(Il2CppExceptionWrapper& e)
	{
		if(il2cpp_codegen_class_is_assignable_from (((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&Exception_t_il2cpp_TypeInfo_var)), il2cpp_codegen_object_class(e.ex)))
		{
			IL2CPP_PUSH_ACTIVE_EXCEPTION(e.ex);
			goto CATCH_007b;
		}
		throw e;
	}

CATCH_007b:
	{// begin catch(System.Exception)
		// catch (Exception e)
		V_2 = ((Exception_t*)IL2CPP_GET_ACTIVE_EXCEPTION(Exception_t*));
		// Debug.LogError("RiseSdk Init Error:::\n" + e.StackTrace + "\n" + e.Message);
		Exception_t* L_20 = V_2;
		NullCheck(L_20);
		String_t* L_21;
		L_21 = VirtualFuncInvoker0< String_t* >::Invoke(8 /* System.String System.Exception::get_StackTrace() */, L_20);
		Exception_t* L_22 = V_2;
		NullCheck(L_22);
		String_t* L_23;
		L_23 = VirtualFuncInvoker0< String_t* >::Invoke(5 /* System.String System.Exception::get_Message() */, L_22);
		String_t* L_24;
		L_24 = String_Concat_m093934F71A9B351911EE46311674ED463B180006(((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral109B9483299C123690E4FB1FBE4442731D158EB5)), L_21, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral00B28FF06B788B9B67C6B259800F404F9F3761FD)), L_23, NULL);
		il2cpp_codegen_runtime_class_init_inline(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var)));
		Debug_LogError_mB00B2B4468EF3CAF041B038D840820FB84C924B2(L_24, NULL);
		// }
		IL2CPP_POP_ACTIVE_EXCEPTION();
		goto IL_009e;
	}// end catch (depth: 1)

IL_009e:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.IRiseSdk::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void IRiseSdk__ctor_m1B1BAD2005EB2D6AD68784DDE4E93173E5802F2B (IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.IRiseSdk/<>c__DisplayClass13_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass13_0__ctor_m38F954BB4142E740723017A0FD28BB04FAF9735E (U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
// System.Void RiseSdk.IRiseSdk/<>c__DisplayClass13_0::<CallSafeOnMainThread>b__0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass13_0_U3CCallSafeOnMainThreadU3Eb__0_mEDBBC07F399DCFF0ABF83639ACF8D9D17B04D5FD (U3CU3Ec__DisplayClass13_0_tEF2268814DD4DCBD957D18C07E6016D3D345A49C* __this, const RuntimeMethod* method) 
{
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* G_B2_0 = NULL;
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* G_B1_0 = NULL;
	{
		// action?.Invoke();
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_0 = __this->___action_0;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		NullCheck(G_B2_0);
		Action_Invoke_m7126A54DACA72B845424072887B5F3A51FC3808E_inline(G_B2_0, NULL);
		// }));
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.IRiseSdk/<>c__DisplayClass14_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass14_0__ctor_m11BC8FD83CD5D255A3E6471C016D2506EEABDE42 (U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
// System.Void RiseSdk.IRiseSdk/<>c__DisplayClass14_0::<CallSafeOnMainThread2>b__0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass14_0_U3CCallSafeOnMainThread2U3Eb__0_m904DAFB6EB35FCAE052734C5BEB981562E3789CB (U3CU3Ec__DisplayClass14_0_t0755F11D69F9D0C5B693F494952AEB98CE75D16B* __this, const RuntimeMethod* method) 
{
	Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* G_B2_0 = NULL;
	Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* G_B1_0 = NULL;
	{
		// action?.Invoke(activity);
		Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* L_0 = __this->___action_0;
		Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_2 = __this->___activity_1;
		NullCheck(G_B2_0);
		Action_1_Invoke_mF1A9E047B412B279EA3AC2BFF9E1754B2C45EA56_inline(G_B2_0, L_2, NULL);
		// }));
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdk::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk__ctor_mDE9BBB5411651A1A94F838AA27F91641B547793E (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, const RuntimeMethod* method) 
{
	{
		// private RiseSdk() { }
		IRiseSdk__ctor_m1B1BAD2005EB2D6AD68784DDE4E93173E5802F2B(__this, NULL);
		// private RiseSdk() { }
		return;
	}
}
// RiseSdk.RiseSdk RiseSdk.RiseSdk::get_Instance()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* RiseSdk_get_Instance_mF7B692D4BF44E697C87CCC5FA0ECCA2C846F54E2 (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Lazy_1_get_Value_m1CFEA24735C8452E6D5A843230294721DBAB2033_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// public static RiseSdk Instance => _instance.Value;
		il2cpp_codegen_runtime_class_init_inline(RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0* L_0 = ((RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var))->____instance_6;
		NullCheck(L_0);
		RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* L_1;
		L_1 = Lazy_1_get_Value_m1CFEA24735C8452E6D5A843230294721DBAB2033(L_0, Lazy_1_get_Value_m1CFEA24735C8452E6D5A843230294721DBAB2033_RuntimeMethod_var);
		return L_1;
	}
}
// System.Void RiseSdk.RiseSdk::OnInit()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_OnInit_m4747EEE5CB19F1A70E13DEC5431364D02ED60B32 (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// RiseSdkListener.Instance.enabled = true;
		RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* L_0;
		L_0 = RiseSdkListener_get_Instance_m866C1EBF47DE935C0E793F917A8ECA725A4817AE(NULL);
		NullCheck(L_0);
		Behaviour_set_enabled_mF1DCFE60EB09E0529FE9476CA804A3AA2D72B16A(L_0, (bool)1, NULL);
		// _riseSdk = new RiseSdkForAndroid();
		RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* L_1 = (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5*)il2cpp_codegen_object_new(RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5_il2cpp_TypeInfo_var);
		NullCheck(L_1);
		RiseSdkForAndroid__ctor_m01D8A1FB5527EB80DB0546B1410761BB485DF8E3(L_1, NULL);
		__this->____riseSdk_7 = L_1;
		Il2CppCodeGenWriteBarrier((void**)(&__this->____riseSdk_7), (void*)L_1);
		// _riseSdk?.OnInit();
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_2 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_3 = L_2;
		G_B1_0 = L_3;
		if (L_3)
		{
			G_B2_0 = L_3;
			goto IL_0021;
		}
	}
	{
		return;
	}

IL_0021:
	{
		NullCheck(G_B2_0);
		VirtualActionInvoker0::Invoke(4 /* System.Void RiseSdk.IRiseSdk::OnInit() */, G_B2_0);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdk::OnStart()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_OnStart_mBF6E60718FE2EB1343F1581A6E5B47FFF4997136 (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, const RuntimeMethod* method) 
{
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// _riseSdk?.OnStart();
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		NullCheck(G_B2_0);
		VirtualActionInvoker0::Invoke(5 /* System.Void RiseSdk.IRiseSdk::OnStart() */, G_B2_0);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdk::OnResume()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_OnResume_mA33FC5F1D19A90BC7A8BB6A3C473AC477C293749 (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, const RuntimeMethod* method) 
{
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// _riseSdk?.OnResume();
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		NullCheck(G_B2_0);
		VirtualActionInvoker0::Invoke(6 /* System.Void RiseSdk.IRiseSdk::OnResume() */, G_B2_0);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdk::OnPause()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_OnPause_m596C65FB4B7985C2471758799D89BBF743D8AC3D (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, const RuntimeMethod* method) 
{
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// _riseSdk?.OnPause();
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		NullCheck(G_B2_0);
		VirtualActionInvoker0::Invoke(7 /* System.Void RiseSdk.IRiseSdk::OnPause() */, G_B2_0);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdk::OnStop()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_OnStop_m2F5A120410DEF65244C8A624B118C52757037687 (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, const RuntimeMethod* method) 
{
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// _riseSdk?.OnStop();
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		NullCheck(G_B2_0);
		VirtualActionInvoker0::Invoke(8 /* System.Void RiseSdk.IRiseSdk::OnStop() */, G_B2_0);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdk::recordException(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_recordException_m7B51DA3E4E21B630D754EFF2C2ECB39567257F1E (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, String_t* ___0_title, String_t* ___1_err, const RuntimeMethod* method) 
{
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// _riseSdk?.recordException(title, err);
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		String_t* L_2 = ___0_title;
		String_t* L_3 = ___1_err;
		NullCheck(G_B2_0);
		VirtualActionInvoker2< String_t*, String_t* >::Invoke(9 /* System.Void RiseSdk.IRiseSdk::recordException(System.String,System.String) */, G_B2_0, L_2, L_3);
		// }
		return;
	}
}
// System.Boolean RiseSdk.RiseSdk::HasRewardAd()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool RiseSdk_HasRewardAd_m1D642CB7EF6CB3B42800CBE95E53D3CF55A57697 (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, const RuntimeMethod* method) 
{
	{
		// if (_riseSdk == null) return false;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		if (L_0)
		{
			goto IL_000a;
		}
	}
	{
		// if (_riseSdk == null) return false;
		return (bool)0;
	}

IL_000a:
	{
		// return _riseSdk.HasRewardAd();
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = __this->____riseSdk_7;
		NullCheck(L_1);
		bool L_2;
		L_2 = VirtualFuncInvoker0< bool >::Invoke(10 /* System.Boolean RiseSdk.IRiseSdk::HasRewardAd() */, L_1);
		return L_2;
	}
}
// System.Void RiseSdk.RiseSdk::ShowRewardAd(System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_ShowRewardAd_m6705DD3FCB52DBC8BF3454E9D149D9F5AADB2F24 (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, int32_t ___0_rewardId, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral36F1A2DE510A345520AE400CB46D98671631B2FD);
		s_Il2CppMethodInitialized = true;
	}
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// Debug.Log("ShowRewardAd " + rewardId);
		String_t* L_0;
		L_0 = Int32_ToString_m030E01C24E294D6762FB0B6F37CB541581F55CA5((&___0_rewardId), NULL);
		String_t* L_1;
		L_1 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(_stringLiteral36F1A2DE510A345520AE400CB46D98671631B2FD, L_0, NULL);
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(L_1, NULL);
		// _riseSdk?.ShowRewardAd(rewardId);
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_2 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_3 = L_2;
		G_B1_0 = L_3;
		if (L_3)
		{
			G_B2_0 = L_3;
			goto IL_0021;
		}
	}
	{
		return;
	}

IL_0021:
	{
		int32_t L_4 = ___0_rewardId;
		NullCheck(G_B2_0);
		VirtualActionInvoker1< int32_t >::Invoke(11 /* System.Void RiseSdk.IRiseSdk::ShowRewardAd(System.Int32) */, G_B2_0, L_4);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdk::ShowRewardAd(System.String,System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_ShowRewardAd_m8348E83EEADC29C635919096AC2C80F082B66309 (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, String_t* ___0_tag, int32_t ___1_rewardId, const RuntimeMethod* method) 
{
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// _riseSdk?.ShowRewardAd(tag, rewardId);
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		String_t* L_2 = ___0_tag;
		int32_t L_3 = ___1_rewardId;
		NullCheck(G_B2_0);
		VirtualActionInvoker2< String_t*, int32_t >::Invoke(12 /* System.Void RiseSdk.IRiseSdk::ShowRewardAd(System.String,System.Int32) */, G_B2_0, L_2, L_3);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdk::FireBaseTrackEvent(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_FireBaseTrackEvent_mAF62EB79C55D9DC8F89A9968B8657C68533D91FF (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, String_t* ___0_category, String_t* ___1_keyValueData, const RuntimeMethod* method) 
{
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// _riseSdk?.FireBaseTrackEvent(category, keyValueData);
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		String_t* L_2 = ___0_category;
		String_t* L_3 = ___1_keyValueData;
		NullCheck(G_B2_0);
		VirtualActionInvoker2< String_t*, String_t* >::Invoke(13 /* System.Void RiseSdk.IRiseSdk::FireBaseTrackEvent(System.String,System.String) */, G_B2_0, L_2, L_3);
		// }
		return;
	}
}
// System.Boolean RiseSdk.RiseSdk::GetAdsEnabledState()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool RiseSdk_GetAdsEnabledState_m7C5C677CFF83D8AD931D3268F749200B62928424 (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, const RuntimeMethod* method) 
{
	{
		// if (_riseSdk == null) return false;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		if (L_0)
		{
			goto IL_000a;
		}
	}
	{
		// if (_riseSdk == null) return false;
		return (bool)0;
	}

IL_000a:
	{
		// return _riseSdk.GetAdsEnabledState();
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = __this->____riseSdk_7;
		NullCheck(L_1);
		bool L_2;
		L_2 = VirtualFuncInvoker0< bool >::Invoke(14 /* System.Boolean RiseSdk.IRiseSdk::GetAdsEnabledState() */, L_1);
		return L_2;
	}
}
// System.Void RiseSdk.RiseSdk::SetAdsEnabledState(System.Boolean)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_SetAdsEnabledState_m499488DAB06FFEE618A64004BBF31DFB2D67312E (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, bool ___0_state, const RuntimeMethod* method) 
{
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// _riseSdk?.SetAdsEnabledState(state);
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		bool L_2 = ___0_state;
		NullCheck(G_B2_0);
		VirtualActionInvoker1< bool >::Invoke(15 /* System.Void RiseSdk.IRiseSdk::SetAdsEnabledState(System.Boolean) */, G_B2_0, L_2);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdk::ToastMsg(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk_ToastMsg_m4952593ED64A820E02905CBCA2DEB89B05C87721 (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* __this, String_t* ___0_str, const RuntimeMethod* method) 
{
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B2_0 = NULL;
	IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* G_B1_0 = NULL;
	{
		// _riseSdk?.ToastMsg(str);
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_0 = __this->____riseSdk_7;
		IRiseSdk_t35FCEF17B8A43522A815134E8841D8C61D57979E* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		String_t* L_2 = ___0_str;
		NullCheck(G_B2_0);
		VirtualActionInvoker1< String_t* >::Invoke(16 /* System.Void RiseSdk.IRiseSdk::ToastMsg(System.String) */, G_B2_0, L_2);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdk::.cctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdk__cctor_mB88385CDDCCF66A8DF1A7E2757A139E2AF68FB65 (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Lazy_1__ctor_mDA25196CB38DB23B1A01521353908954A84CDEDA_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec_U3C_cctorU3Eb__31_0_mD8C63722A480DC75713E42C5649AEF00E1927A25_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// private static readonly Lazy<RiseSdk> _instance = new Lazy<RiseSdk>(() => new RiseSdk());
		il2cpp_codegen_runtime_class_init_inline(U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_il2cpp_TypeInfo_var);
		U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA* L_0 = ((U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_StaticFields*)il2cpp_codegen_static_fields_for(U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_il2cpp_TypeInfo_var))->___U3CU3E9_0;
		Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39* L_1 = (Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39*)il2cpp_codegen_object_new(Func_1_t069A5548A170E7B1764C6665E295E577AA0FEE39_il2cpp_TypeInfo_var);
		NullCheck(L_1);
		Func_1__ctor_m573904768C47610B49696F3E8A4A9E15D206594A(L_1, L_0, (intptr_t)((void*)U3CU3Ec_U3C_cctorU3Eb__31_0_mD8C63722A480DC75713E42C5649AEF00E1927A25_RuntimeMethod_var), NULL);
		Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0* L_2 = (Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0*)il2cpp_codegen_object_new(Lazy_1_t42C9BD63DCF8A1648F582BA39EBE1DDFFF7217A0_il2cpp_TypeInfo_var);
		NullCheck(L_2);
		Lazy_1__ctor_mDA25196CB38DB23B1A01521353908954A84CDEDA(L_2, L_1, Lazy_1__ctor_mDA25196CB38DB23B1A01521353908954A84CDEDA_RuntimeMethod_var);
		((RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var))->____instance_6 = L_2;
		Il2CppCodeGenWriteBarrier((void**)(&((RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var))->____instance_6), (void*)L_2);
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdk/<>c::.cctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__cctor_mAF83020F7F230A400B720E8186071B62F41FD639 (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA* L_0 = (U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA*)il2cpp_codegen_object_new(U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		U3CU3Ec__ctor_m001E179ABE86775932652250A7338E2FB003B3FB(L_0, NULL);
		((U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_StaticFields*)il2cpp_codegen_static_fields_for(U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_il2cpp_TypeInfo_var))->___U3CU3E9_0 = L_0;
		Il2CppCodeGenWriteBarrier((void**)(&((U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_StaticFields*)il2cpp_codegen_static_fields_for(U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA_il2cpp_TypeInfo_var))->___U3CU3E9_0), (void*)L_0);
		return;
	}
}
// System.Void RiseSdk.RiseSdk/<>c::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__ctor_m001E179ABE86775932652250A7338E2FB003B3FB (U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
// RiseSdk.RiseSdk RiseSdk.RiseSdk/<>c::<.cctor>b__31_0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* U3CU3Ec_U3C_cctorU3Eb__31_0_mD8C63722A480DC75713E42C5649AEF00E1927A25 (U3CU3Ec_t26E3F21CDCB2CC7A2B80544190F1358A566816CA* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// private static readonly Lazy<RiseSdk> _instance = new Lazy<RiseSdk>(() => new RiseSdk());
		RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* L_0 = (RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F*)il2cpp_codegen_object_new(RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		RiseSdk__ctor_mDE9BBB5411651A1A94F838AA27F91641B547793E(L_0, NULL);
		return L_0;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdkForAndroid::set__javaClass(UnityEngine.AndroidJavaClass)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_set__javaClass_m6A9322C6F9A1961B8A3591EBCFDF9C2157E334A5 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* ___0_value, const RuntimeMethod* method) 
{
	{
		// _class = value;
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_0 = ___0_value;
		__this->____class_0 = L_0;
		Il2CppCodeGenWriteBarrier((void**)(&__this->____class_0), (void*)L_0);
		// }
		return;
	}
}
// UnityEngine.AndroidJavaClass RiseSdk.RiseSdkForAndroid::get__javaClass()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralF0DCFEDBF422508D62EFFA422BCCE4309448C1E9);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	intptr_t V_1;
	memset((&V_1), 0, sizeof(V_1));
	{
		// if (_class == null)
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_0 = __this->____class_0;
		if (L_0)
		{
			goto IL_0018;
		}
	}
	{
		// _class = new AndroidJavaClass("com.android.client.Unity");
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1 = (AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03*)il2cpp_codegen_object_new(AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var);
		NullCheck(L_1);
		AndroidJavaClass__ctor_mB5466169E1151B8CC44C8FED234D79984B431389(L_1, _stringLiteralF0DCFEDBF422508D62EFFA422BCCE4309448C1E9, NULL);
		__this->____class_0 = L_1;
		Il2CppCodeGenWriteBarrier((void**)(&__this->____class_0), (void*)L_1);
	}

IL_0018:
	{
		// if (_class != null)
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_2 = __this->____class_0;
		if (!L_2)
		{
			goto IL_0062;
		}
	}
	{
		// string packageNameWithClassName = _class.GetRawClass().ToString();
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_3 = __this->____class_0;
		NullCheck(L_3);
		intptr_t L_4;
		L_4 = AndroidJavaObject_GetRawClass_mE4FB4DC4F856A52E10C6AAD0B65BEBF47B5071F5(L_3, NULL);
		V_1 = L_4;
		String_t* L_5;
		L_5 = IntPtr_ToString_mDA1150F4802E1FB9313FB4E32C684EEE19116E5C((&V_1), NULL);
		V_0 = L_5;
		// if (packageNameWithClassName != null && "com.android.client.Unity".Equals(packageNameWithClassName))
		String_t* L_6 = V_0;
		if (!L_6)
		{
			goto IL_004b;
		}
	}
	{
		String_t* L_7 = V_0;
		NullCheck(_stringLiteralF0DCFEDBF422508D62EFFA422BCCE4309448C1E9);
		bool L_8;
		L_8 = String_Equals_mCD5F35DEDCAFE51ACD4E033726FC2EF8DF7E9B4D(_stringLiteralF0DCFEDBF422508D62EFFA422BCCE4309448C1E9, L_7, NULL);
		if (!L_8)
		{
			goto IL_004b;
		}
	}
	{
		// return _class;
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_9 = __this->____class_0;
		return L_9;
	}

IL_004b:
	{
		// _class = null;
		__this->____class_0 = (AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03*)NULL;
		Il2CppCodeGenWriteBarrier((void**)(&__this->____class_0), (void*)(AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03*)NULL);
		// _class = new AndroidJavaClass("com.android.client.Unity");
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_10 = (AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03*)il2cpp_codegen_object_new(AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var);
		NullCheck(L_10);
		AndroidJavaClass__ctor_mB5466169E1151B8CC44C8FED234D79984B431389(L_10, _stringLiteralF0DCFEDBF422508D62EFFA422BCCE4309448C1E9, NULL);
		__this->____class_0 = L_10;
		Il2CppCodeGenWriteBarrier((void**)(&__this->____class_0), (void*)L_10);
	}

IL_0062:
	{
		// return _class;
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_11 = __this->____class_0;
		return L_11;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::OnInit()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_OnInit_mA8227F29BF03D28D5D8F4FB67841D1A1B78D446F (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkForAndroid_U3COnInitU3Eb__4_0_m1082EE1D20F7ACFE9BBF7AF99290E1E4CA36E8B9_RuntimeMethod_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (Application.platform == RuntimePlatform.Android)
		int32_t L_0;
		L_0 = Application_get_platform_m59EF7D6155D18891B24767F83F388160B1FF2138(NULL);
		if ((!(((uint32_t)L_0) == ((uint32_t)((int32_t)11)))))
		{
			goto IL_001b;
		}
	}
	{
		// CallSafeOnMainThread2((activity) =>
		// {
		//     _javaClass?.CallStatic("onCreate", activity);
		// });
		Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594* L_1 = (Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594*)il2cpp_codegen_object_new(Action_1_tBD9457F0872CC7E6C1468CA37E6EF9D4403E1594_il2cpp_TypeInfo_var);
		NullCheck(L_1);
		Action_1__ctor_mAD4F64E1D47B76FC413CEBA12F172E968C83AD04(L_1, __this, (intptr_t)((void*)RiseSdkForAndroid_U3COnInitU3Eb__4_0_m1082EE1D20F7ACFE9BBF7AF99290E1E4CA36E8B9_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread2_mC76A4339B51170DA0BE042688ED53D5F3EBCEDAA(__this, L_1, NULL);
	}

IL_001b:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::OnPause()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_OnPause_mF2397BA0073C33D96EF3D295A32987B9B830BB75 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkForAndroid_U3COnPauseU3Eb__5_0_m34FB82A87E0DD7F49A453FBF31E608F8A88FDDF0_RuntimeMethod_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// CallSafeOnMainThread(() =>
		// {
		//     _javaClass?.CallStatic("onPause");
		// });
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_0 = (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)il2cpp_codegen_object_new(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC(L_0, __this, (intptr_t)((void*)RiseSdkForAndroid_U3COnPauseU3Eb__5_0_m34FB82A87E0DD7F49A453FBF31E608F8A88FDDF0_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57(__this, L_0, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::OnResume()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_OnResume_mA8754E84140080E8834520250C2189E217726179 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkForAndroid_U3COnResumeU3Eb__6_0_m0135D676E784975A363AB0DD9D97D9E0AA942A11_RuntimeMethod_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// CallSafeOnMainThread(() =>
		// {
		//     _javaClass?.CallStatic("onResume");
		// });
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_0 = (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)il2cpp_codegen_object_new(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC(L_0, __this, (intptr_t)((void*)RiseSdkForAndroid_U3COnResumeU3Eb__6_0_m0135D676E784975A363AB0DD9D97D9E0AA942A11_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57(__this, L_0, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::OnStart()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_OnStart_m060DCCD1ECA280E391A6D24054299A5C972411AA (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkForAndroid_U3COnStartU3Eb__7_0_m89B8844DF78386CA08E46BEF64616448A7DB922D_RuntimeMethod_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// CallSafeOnMainThread(() =>
		// {
		//     _javaClass?.CallStatic("onStart");
		// });
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_0 = (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)il2cpp_codegen_object_new(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC(L_0, __this, (intptr_t)((void*)RiseSdkForAndroid_U3COnStartU3Eb__7_0_m89B8844DF78386CA08E46BEF64616448A7DB922D_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57(__this, L_0, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::OnStop()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_OnStop_m4116B9980F3075EE0F6EB8798BD9801EE36BCEFE (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkForAndroid_U3COnStopU3Eb__8_0_m06252C6CA773D404BD4F852CF7D0C77448A806C0_RuntimeMethod_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// CallSafeOnMainThread(() =>
		// {
		//     _javaClass?.CallStatic("onStop");
		// });
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_0 = (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)il2cpp_codegen_object_new(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC(L_0, __this, (intptr_t)((void*)RiseSdkForAndroid_U3COnStopU3Eb__8_0_m06252C6CA773D404BD4F852CF7D0C77448A806C0_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57(__this, L_0, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::FireBaseTrackEvent(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_FireBaseTrackEvent_m062D1BAC681A4EADE4697488A71BD69448400C6D (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, String_t* ___0_category, String_t* ___1_keyValueData, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass9_0_U3CFireBaseTrackEventU3Eb__0_m2025C69CA2D4A54ED89390426A9209D30187CC82_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D* V_0 = NULL;
	{
		U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D* L_0 = (U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D*)il2cpp_codegen_object_new(U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		U3CU3Ec__DisplayClass9_0__ctor_m4E08A045DF1F294C589299B9E4C206EB557B91BB(L_0, NULL);
		V_0 = L_0;
		U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D* L_1 = V_0;
		NullCheck(L_1);
		L_1->___U3CU3E4__this_0 = __this;
		Il2CppCodeGenWriteBarrier((void**)(&L_1->___U3CU3E4__this_0), (void*)__this);
		U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D* L_2 = V_0;
		String_t* L_3 = ___0_category;
		NullCheck(L_2);
		L_2->___category_1 = L_3;
		Il2CppCodeGenWriteBarrier((void**)(&L_2->___category_1), (void*)L_3);
		U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D* L_4 = V_0;
		String_t* L_5 = ___1_keyValueData;
		NullCheck(L_4);
		L_4->___keyValueData_2 = L_5;
		Il2CppCodeGenWriteBarrier((void**)(&L_4->___keyValueData_2), (void*)L_5);
		// CallSafeOnMainThread(() =>
		// {
		//     _javaClass?.CallStatic("trackEventToFirebase", category, keyValueData);
		// });
		U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D* L_6 = V_0;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_7 = (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)il2cpp_codegen_object_new(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		NullCheck(L_7);
		Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC(L_7, L_6, (intptr_t)((void*)U3CU3Ec__DisplayClass9_0_U3CFireBaseTrackEventU3Eb__0_m2025C69CA2D4A54ED89390426A9209D30187CC82_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57(__this, L_7, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::recordException(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_recordException_m1F49CB0609A9C4B11A483D37349EB011E13DDA10 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, String_t* ___0_title, String_t* ___1_err, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass10_0_U3CrecordExceptionU3Eb__0_m04E2BE8643342EB115E4B797B549DCB094F42C9C_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D* V_0 = NULL;
	{
		U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D* L_0 = (U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D*)il2cpp_codegen_object_new(U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		U3CU3Ec__DisplayClass10_0__ctor_m99B4257ECBAFE4E1665ACEAE43D93CFD88337F2F(L_0, NULL);
		V_0 = L_0;
		U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D* L_1 = V_0;
		NullCheck(L_1);
		L_1->___U3CU3E4__this_0 = __this;
		Il2CppCodeGenWriteBarrier((void**)(&L_1->___U3CU3E4__this_0), (void*)__this);
		U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D* L_2 = V_0;
		String_t* L_3 = ___0_title;
		NullCheck(L_2);
		L_2->___title_1 = L_3;
		Il2CppCodeGenWriteBarrier((void**)(&L_2->___title_1), (void*)L_3);
		U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D* L_4 = V_0;
		String_t* L_5 = ___1_err;
		NullCheck(L_4);
		L_4->___err_2 = L_5;
		Il2CppCodeGenWriteBarrier((void**)(&L_4->___err_2), (void*)L_5);
		// CallSafeOnMainThread(() =>
		// {
		//     _javaClass?.CallStatic("recordException", title, err);
		// });
		U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D* L_6 = V_0;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_7 = (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)il2cpp_codegen_object_new(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		NullCheck(L_7);
		Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC(L_7, L_6, (intptr_t)((void*)U3CU3Ec__DisplayClass10_0_U3CrecordExceptionU3Eb__0_m04E2BE8643342EB115E4B797B549DCB094F42C9C_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57(__this, L_7, NULL);
		// }
		return;
	}
}
// System.Boolean RiseSdk.RiseSdkForAndroid::HasRewardAd()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool RiseSdkForAndroid_HasRewardAd_mB47685C4437677962B710C15077902D4E8DE9733 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaObject_CallStatic_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mE956BC9A30BEC746DE593C53C1B8DB6A685185A6_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral79CD4A07010B3D7A9E573D3FCBE8E6B7EEFFABD3);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralF17AE3F6EE79ABAD493C10DCE41D5CDBDA4D91E9);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralFC9082054BCCBFE90F98613132B91AB86CA0B6EA);
		s_Il2CppMethodInitialized = true;
	}
	{
		// Debug.Log("Java class call from android");
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(_stringLiteralF17AE3F6EE79ABAD493C10DCE41D5CDBDA4D91E9, NULL);
		// if (_javaClass == null)
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_0;
		L_0 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(__this, NULL);
		if (L_0)
		{
			goto IL_001e;
		}
	}
	{
		// Debug.Log("Java class is null");
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(_stringLiteralFC9082054BCCBFE90F98613132B91AB86CA0B6EA, NULL);
		// return false;
		return (bool)0;
	}

IL_001e:
	{
		// return _javaClass.CallStatic<bool>("hasRewardAd");
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1;
		L_1 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(__this, NULL);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_2;
		L_2 = Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_inline(Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		NullCheck(L_1);
		bool L_3;
		L_3 = AndroidJavaObject_CallStatic_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mE956BC9A30BEC746DE593C53C1B8DB6A685185A6(L_1, _stringLiteral79CD4A07010B3D7A9E573D3FCBE8E6B7EEFFABD3, L_2, AndroidJavaObject_CallStatic_TisBoolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_mE956BC9A30BEC746DE593C53C1B8DB6A685185A6_RuntimeMethod_var);
		return L_3;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::ShowRewardAd(System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_ShowRewardAd_mDF98AA99702D3295A05AB1B56C60C6D42A530B29 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, int32_t ___0_rewardId, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass12_0_U3CShowRewardAdU3Eb__0_m9A7E731D46232CFFB16D0374F9E423976B407617_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76* V_0 = NULL;
	{
		U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76* L_0 = (U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76*)il2cpp_codegen_object_new(U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		U3CU3Ec__DisplayClass12_0__ctor_m9F65A8111F1102D7AA6D272EA90DF0542FFF4694(L_0, NULL);
		V_0 = L_0;
		U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76* L_1 = V_0;
		NullCheck(L_1);
		L_1->___U3CU3E4__this_0 = __this;
		Il2CppCodeGenWriteBarrier((void**)(&L_1->___U3CU3E4__this_0), (void*)__this);
		U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76* L_2 = V_0;
		int32_t L_3 = ___0_rewardId;
		NullCheck(L_2);
		L_2->___rewardId_1 = L_3;
		// CallSafeOnMainThread(() =>
		// {
		//     _javaClass?.CallStatic("showRewardAd", rewardId);
		// });
		U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76* L_4 = V_0;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_5 = (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)il2cpp_codegen_object_new(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		NullCheck(L_5);
		Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC(L_5, L_4, (intptr_t)((void*)U3CU3Ec__DisplayClass12_0_U3CShowRewardAdU3Eb__0_m9A7E731D46232CFFB16D0374F9E423976B407617_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57(__this, L_5, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::ShowRewardAd(System.String,System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_ShowRewardAd_mDB0E8AC10A349CAF448EA988CC6582E2CCFEAED8 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, String_t* ___0_tag, int32_t ___1_rewardId, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass13_0_U3CShowRewardAdU3Eb__0_m3DB00AEFDF6C1BF09F3E5BA65981A7E529730D98_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D* V_0 = NULL;
	{
		U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D* L_0 = (U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D*)il2cpp_codegen_object_new(U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		U3CU3Ec__DisplayClass13_0__ctor_m9FA6F3648E6C607FBBC42BFADB46E0F5523C3F67(L_0, NULL);
		V_0 = L_0;
		U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D* L_1 = V_0;
		NullCheck(L_1);
		L_1->___U3CU3E4__this_0 = __this;
		Il2CppCodeGenWriteBarrier((void**)(&L_1->___U3CU3E4__this_0), (void*)__this);
		U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D* L_2 = V_0;
		String_t* L_3 = ___0_tag;
		NullCheck(L_2);
		L_2->___tag_1 = L_3;
		Il2CppCodeGenWriteBarrier((void**)(&L_2->___tag_1), (void*)L_3);
		U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D* L_4 = V_0;
		int32_t L_5 = ___1_rewardId;
		NullCheck(L_4);
		L_4->___rewardId_2 = L_5;
		// CallSafeOnMainThread(() =>
		// {
		//     _javaClass?.CallStatic("showRewardAd", tag, rewardId);
		// });
		U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D* L_6 = V_0;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_7 = (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)il2cpp_codegen_object_new(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		NullCheck(L_7);
		Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC(L_7, L_6, (intptr_t)((void*)U3CU3Ec__DisplayClass13_0_U3CShowRewardAdU3Eb__0_m3DB00AEFDF6C1BF09F3E5BA65981A7E529730D98_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57(__this, L_7, NULL);
		// }
		return;
	}
}
// System.Boolean RiseSdk.RiseSdkForAndroid::GetAdsEnabledState()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool RiseSdkForAndroid_GetAdsEnabledState_mE5314798F7477ACF32A0424FEDC51ECE96778B77 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	{
		// return true;
		return (bool)1;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::SetAdsEnabledState(System.Boolean)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_SetAdsEnabledState_m7FCAB17D2C34A4557EACC98905D733BC5BF1B3E3 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, bool ___0_state, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral875A41C8F1EEC1DEF871C0E7703977E3BC52359F);
		s_Il2CppMethodInitialized = true;
	}
	{
		// Debug.Log($"Android set ads enabled state: {state}");
		bool L_0 = ___0_state;
		bool L_1 = L_0;
		RuntimeObject* L_2 = Box(Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var, &L_1);
		String_t* L_3;
		L_3 = String_Format_mA8DBB4C2516B9723C5A41E6CB1E2FAF4BBE96DD8(_stringLiteral875A41C8F1EEC1DEF871C0E7703977E3BC52359F, L_2, NULL);
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(L_3, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::ToastMsg(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_ToastMsg_mD0712C930F8426D93AD6980D016DC7199410EED1 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, String_t* ___0_str, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass16_0_U3CToastMsgU3Eb__0_mBC6044148B319EB50771BD88920AC3718077E536_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA* V_0 = NULL;
	{
		U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA* L_0 = (U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA*)il2cpp_codegen_object_new(U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA_il2cpp_TypeInfo_var);
		NullCheck(L_0);
		U3CU3Ec__DisplayClass16_0__ctor_m0A7A31947CA27FDF4CD4DB3E89B70B5B059DE832(L_0, NULL);
		V_0 = L_0;
		U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA* L_1 = V_0;
		NullCheck(L_1);
		L_1->___U3CU3E4__this_0 = __this;
		Il2CppCodeGenWriteBarrier((void**)(&L_1->___U3CU3E4__this_0), (void*)__this);
		U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA* L_2 = V_0;
		String_t* L_3 = ___0_str;
		NullCheck(L_2);
		L_2->___str_1 = L_3;
		Il2CppCodeGenWriteBarrier((void**)(&L_2->___str_1), (void*)L_3);
		//   CallSafeOnMainThread(() =>
		// {
		//     _javaClass?.CallStatic("toast", str);
		// });
		U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA* L_4 = V_0;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_5 = (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)il2cpp_codegen_object_new(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		NullCheck(L_5);
		Action__ctor_mBDC7B0B4A3F583B64C2896F01BDED360772F67DC(L_5, L_4, (intptr_t)((void*)U3CU3Ec__DisplayClass16_0_U3CToastMsgU3Eb__0_mBC6044148B319EB50771BD88920AC3718077E536_RuntimeMethod_var), NULL);
		IRiseSdk_CallSafeOnMainThread_mB827D8BE948F106B3CA1B4D617BAAE19E6324F57(__this, L_5, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid__ctor_m01D8A1FB5527EB80DB0546B1410761BB485DF8E3 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	{
		IRiseSdk__ctor_m1B1BAD2005EB2D6AD68784DDE4E93173E5802F2B(__this, NULL);
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::<OnInit>b__4_0(UnityEngine.AndroidJavaObject)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_U3COnInitU3Eb__4_0_m1082EE1D20F7ACFE9BBF7AF99290E1E4CA36E8B9 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* ___0_activity, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral007AE806CCD78C75FA7CA260F0C6D94D9A985311);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("onCreate", activity);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_0;
		L_0 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(__this, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_2 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)1);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_3 = L_2;
		AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_4 = ___0_activity;
		NullCheck(L_3);
		ArrayElementTypeCheck (L_3, L_4);
		(L_3)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_4);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteral007AE806CCD78C75FA7CA260F0C6D94D9A985311, L_3, NULL);
		// });
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::<OnPause>b__5_0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_U3COnPauseU3Eb__5_0_m34FB82A87E0DD7F49A453FBF31E608F8A88FDDF0 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralC386789464914AFAB69C70A3E07C88FB68EDB4DB);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("onPause");
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_0;
		L_0 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(__this, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_2;
		L_2 = Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_inline(Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteralC386789464914AFAB69C70A3E07C88FB68EDB4DB, L_2, NULL);
		// });
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::<OnResume>b__6_0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_U3COnResumeU3Eb__6_0_m0135D676E784975A363AB0DD9D97D9E0AA942A11 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral4DD298A95417FB51C736D39EC400C2991FE38DDE);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("onResume");
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_0;
		L_0 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(__this, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_2;
		L_2 = Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_inline(Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteral4DD298A95417FB51C736D39EC400C2991FE38DDE, L_2, NULL);
		// });
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::<OnStart>b__7_0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_U3COnStartU3Eb__7_0_m89B8844DF78386CA08E46BEF64616448A7DB922D (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralD766ED5EEC6B619ADF99610DC9A7EFCBA8F696BA);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("onStart");
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_0;
		L_0 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(__this, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_2;
		L_2 = Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_inline(Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteralD766ED5EEC6B619ADF99610DC9A7EFCBA8F696BA, L_2, NULL);
		// });
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid::<OnStop>b__8_0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForAndroid_U3COnStopU3Eb__8_0_m06252C6CA773D404BD4F852CF7D0C77448A806C0 (RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral14386C5B349364790D8B50DC2C99DA84A1515FD8);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("onStop");
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_0;
		L_0 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(__this, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1 = L_0;
		G_B1_0 = L_1;
		if (L_1)
		{
			G_B2_0 = L_1;
			goto IL_000b;
		}
	}
	{
		return;
	}

IL_000b:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_2;
		L_2 = Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_inline(Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteral14386C5B349364790D8B50DC2C99DA84A1515FD8, L_2, NULL);
		// });
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass9_0__ctor_m4E08A045DF1F294C589299B9E4C206EB557B91BB (U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass9_0::<FireBaseTrackEvent>b__0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass9_0_U3CFireBaseTrackEventU3Eb__0_m2025C69CA2D4A54ED89390426A9209D30187CC82 (U3CU3Ec__DisplayClass9_0_tE882A70AF89C5C90470D4C1070555A7AC4DD2E5D* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral75BA519FAA66BEB6FD5B3EEE9D8CA9B41F1D2549);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("trackEventToFirebase", category, keyValueData);
		RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* L_0 = __this->___U3CU3E4__this_0;
		NullCheck(L_0);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1;
		L_1 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(L_0, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_2 = L_1;
		G_B1_0 = L_2;
		if (L_2)
		{
			G_B2_0 = L_2;
			goto IL_0010;
		}
	}
	{
		return;
	}

IL_0010:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_3 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)2);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_4 = L_3;
		String_t* L_5 = __this->___category_1;
		NullCheck(L_4);
		ArrayElementTypeCheck (L_4, L_5);
		(L_4)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_5);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_6 = L_4;
		String_t* L_7 = __this->___keyValueData_2;
		NullCheck(L_6);
		ArrayElementTypeCheck (L_6, L_7);
		(L_6)->SetAt(static_cast<il2cpp_array_size_t>(1), (RuntimeObject*)L_7);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteral75BA519FAA66BEB6FD5B3EEE9D8CA9B41F1D2549, L_6, NULL);
		// });
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass10_0__ctor_m99B4257ECBAFE4E1665ACEAE43D93CFD88337F2F (U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass10_0::<recordException>b__0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass10_0_U3CrecordExceptionU3Eb__0_m04E2BE8643342EB115E4B797B549DCB094F42C9C (U3CU3Ec__DisplayClass10_0_t65612D972D87122B77BC44FCE998528BB96DDB1D* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDDB5C5392F2AC4F8FA233E2838582017917B2D60);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("recordException", title, err);
		RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* L_0 = __this->___U3CU3E4__this_0;
		NullCheck(L_0);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1;
		L_1 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(L_0, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_2 = L_1;
		G_B1_0 = L_2;
		if (L_2)
		{
			G_B2_0 = L_2;
			goto IL_0010;
		}
	}
	{
		return;
	}

IL_0010:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_3 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)2);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_4 = L_3;
		String_t* L_5 = __this->___title_1;
		NullCheck(L_4);
		ArrayElementTypeCheck (L_4, L_5);
		(L_4)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_5);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_6 = L_4;
		String_t* L_7 = __this->___err_2;
		NullCheck(L_6);
		ArrayElementTypeCheck (L_6, L_7);
		(L_6)->SetAt(static_cast<il2cpp_array_size_t>(1), (RuntimeObject*)L_7);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteralDDB5C5392F2AC4F8FA233E2838582017917B2D60, L_6, NULL);
		// });
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass12_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass12_0__ctor_m9F65A8111F1102D7AA6D272EA90DF0542FFF4694 (U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass12_0::<ShowRewardAd>b__0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass12_0_U3CShowRewardAdU3Eb__0_m9A7E731D46232CFFB16D0374F9E423976B407617 (U3CU3Ec__DisplayClass12_0_tE09AD1268E7EAFD2DAB5C18569A04BA00B77BC76* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralBE120FB49F923996294FC08FABA8FD2AE35AB761);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("showRewardAd", rewardId);
		RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* L_0 = __this->___U3CU3E4__this_0;
		NullCheck(L_0);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1;
		L_1 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(L_0, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_2 = L_1;
		G_B1_0 = L_2;
		if (L_2)
		{
			G_B2_0 = L_2;
			goto IL_0010;
		}
	}
	{
		return;
	}

IL_0010:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_3 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)1);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_4 = L_3;
		int32_t L_5 = __this->___rewardId_1;
		int32_t L_6 = L_5;
		RuntimeObject* L_7 = Box(Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var, &L_6);
		NullCheck(L_4);
		ArrayElementTypeCheck (L_4, L_7);
		(L_4)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_7);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteralBE120FB49F923996294FC08FABA8FD2AE35AB761, L_4, NULL);
		// });
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass13_0__ctor_m9FA6F3648E6C607FBBC42BFADB46E0F5523C3F67 (U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass13_0::<ShowRewardAd>b__0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass13_0_U3CShowRewardAdU3Eb__0_m3DB00AEFDF6C1BF09F3E5BA65981A7E529730D98 (U3CU3Ec__DisplayClass13_0_t0EC835250E1BBB998D99F2B1B0439F187FE3183D* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralBE120FB49F923996294FC08FABA8FD2AE35AB761);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("showRewardAd", tag, rewardId);
		RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* L_0 = __this->___U3CU3E4__this_0;
		NullCheck(L_0);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1;
		L_1 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(L_0, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_2 = L_1;
		G_B1_0 = L_2;
		if (L_2)
		{
			G_B2_0 = L_2;
			goto IL_0010;
		}
	}
	{
		return;
	}

IL_0010:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_3 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)2);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_4 = L_3;
		String_t* L_5 = __this->___tag_1;
		NullCheck(L_4);
		ArrayElementTypeCheck (L_4, L_5);
		(L_4)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_5);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_6 = L_4;
		int32_t L_7 = __this->___rewardId_2;
		int32_t L_8 = L_7;
		RuntimeObject* L_9 = Box(Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var, &L_8);
		NullCheck(L_6);
		ArrayElementTypeCheck (L_6, L_9);
		(L_6)->SetAt(static_cast<il2cpp_array_size_t>(1), (RuntimeObject*)L_9);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteralBE120FB49F923996294FC08FABA8FD2AE35AB761, L_6, NULL);
		// });
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass16_0::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass16_0__ctor_m0A7A31947CA27FDF4CD4DB3E89B70B5B059DE832 (U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA* __this, const RuntimeMethod* method) 
{
	{
		Object__ctor_mE837C6B9FA8C6D5D109F4B2EC885D79919AC0EA2(__this, NULL);
		return;
	}
}
// System.Void RiseSdk.RiseSdkForAndroid/<>c__DisplayClass16_0::<ToastMsg>b__0()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void U3CU3Ec__DisplayClass16_0_U3CToastMsgU3Eb__0_mBC6044148B319EB50771BD88920AC3718077E536 (U3CU3Ec__DisplayClass16_0_t8D1D91BA5F8CBA55E907B706510692280BAC10AA* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralA77B332C46D848F790D53EDB5E0641C50ED83741);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B2_0 = NULL;
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* G_B1_0 = NULL;
	{
		// _javaClass?.CallStatic("toast", str);
		RiseSdkForAndroid_t284CD814E2C382ED64A7A180C4AEA9BA00B4F0F5* L_0 = __this->___U3CU3E4__this_0;
		NullCheck(L_0);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1;
		L_1 = RiseSdkForAndroid_get__javaClass_m3FD5FC17B9150EE39FF739E0AF15BB5F406BB7B9(L_0, NULL);
		AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_2 = L_1;
		G_B1_0 = L_2;
		if (L_2)
		{
			G_B2_0 = L_2;
			goto IL_0010;
		}
	}
	{
		return;
	}

IL_0010:
	{
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_3 = (ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918*)SZArrayNew(ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918_il2cpp_TypeInfo_var, (uint32_t)1);
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_4 = L_3;
		String_t* L_5 = __this->___str_1;
		NullCheck(L_4);
		ArrayElementTypeCheck (L_4, L_5);
		(L_4)->SetAt(static_cast<il2cpp_array_size_t>(0), (RuntimeObject*)L_5);
		NullCheck(G_B2_0);
		AndroidJavaObject_CallStatic_mB677DE04369EDD8E6DECAF2F233116EE1F06555C(G_B2_0, _stringLiteralA77B332C46D848F790D53EDB5E0641C50ED83741, L_4, NULL);
		// });
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdkForEditor::OnInit()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_OnInit_m90FA4B679674752F24FE7EA498141E218AAE9BF9 (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral7A61131F8C86302B298AECB0C8394280E5DAE93E);
		s_Il2CppMethodInitialized = true;
	}
	{
		// Debug.Log($"Editor call Init");
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(_stringLiteral7A61131F8C86302B298AECB0C8394280E5DAE93E, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::OnPause()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_OnPause_m0C2D8D7625CA377CE889855D1E8C0CBA222E4861 (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::OnResume()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_OnResume_m37E01686D0738DF58CC712200FDCFCB8FD7F55D4 (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::OnStart()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_OnStart_m90F0F9B05D3E40B472C757C9C38DA28185138C0E (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::OnStop()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_OnStop_m6BC12D8013C230F43C40A0BF1968D219C11A3B40 (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::FireBaseTrackEvent(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_FireBaseTrackEvent_mCAFCF8470C0F368CD93EC3E481552A8740DB8BFF (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, String_t* ___0_category, String_t* ___1_keyValueData, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral49E1E63F024DAB6D15BFDFE31F390544A9410DB2);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral876C4B39B6E4D0187090400768899C71D99DE90D);
		s_Il2CppMethodInitialized = true;
	}
	{
		// Debug.Log($"track event to firebase:{category}:{keyValueData}");
		String_t* L_0 = ___0_category;
		String_t* L_1 = ___1_keyValueData;
		String_t* L_2;
		L_2 = String_Concat_m093934F71A9B351911EE46311674ED463B180006(_stringLiteral49E1E63F024DAB6D15BFDFE31F390544A9410DB2, L_0, _stringLiteral876C4B39B6E4D0187090400768899C71D99DE90D, L_1, NULL);
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(L_2, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::recordException(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_recordException_mA9AAA2E3964BEAD95397CAAF56A7D95CE7D52F47 (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, String_t* ___0_title, String_t* ___1_err, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral876C4B39B6E4D0187090400768899C71D99DE90D);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralEEB922CBCE2FEF1F2C3AE253CCEBC5CD15A2CE58);
		s_Il2CppMethodInitialized = true;
	}
	{
		// Debug.Log($"log exception to firebase:{title}:{err}");
		String_t* L_0 = ___0_title;
		String_t* L_1 = ___1_err;
		String_t* L_2;
		L_2 = String_Concat_m093934F71A9B351911EE46311674ED463B180006(_stringLiteralEEB922CBCE2FEF1F2C3AE253CCEBC5CD15A2CE58, L_0, _stringLiteral876C4B39B6E4D0187090400768899C71D99DE90D, L_1, NULL);
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(L_2, NULL);
		// }
		return;
	}
}
// System.Boolean RiseSdk.RiseSdkForEditor::HasRewardAd()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool RiseSdkForEditor_HasRewardAd_mD247D935EF274F89D94F9EDE6386EE98FC296FCD (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, const RuntimeMethod* method) 
{
	{
		// return Random.Range(0, 100) % 2 == 0;
		int32_t L_0;
		L_0 = Random_Range_m6763D9767F033357F88B6637F048F4ACA4123B68(0, ((int32_t)100), NULL);
		return (bool)((((int32_t)((int32_t)(L_0%2))) == ((int32_t)0))? 1 : 0);
	}
}
// System.Void RiseSdk.RiseSdkForEditor::ShowRewardAd(System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_ShowRewardAd_m2D86C41F2900EFB5E89EE8B40C7210D96B8F865A (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, int32_t ___0_rewardId, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral43DF4C12BD273F8942F5D6473A5E0E8A745FD24D);
		s_Il2CppMethodInitialized = true;
	}
	{
		// Debug.Log($"Editor show reward ad with reward id:{rewardId}");
		int32_t L_0 = ___0_rewardId;
		int32_t L_1 = L_0;
		RuntimeObject* L_2 = Box(Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var, &L_1);
		String_t* L_3;
		L_3 = String_Format_mA8DBB4C2516B9723C5A41E6CB1E2FAF4BBE96DD8(_stringLiteral43DF4C12BD273F8942F5D6473A5E0E8A745FD24D, L_2, NULL);
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(L_3, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::ShowRewardAd(System.String,System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_ShowRewardAd_m1C68899E91EBABB94A91268A5A6FDC8E1088FE47 (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, String_t* ___0_tag, int32_t ___1_rewardId, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral8F9B51CCF3AF3FFAF9266651B8748CEC7B6E3F23);
		s_Il2CppMethodInitialized = true;
	}
	{
		// Debug.Log($"Editor show reward ad with tag:{tag} and reward id:{rewardId}");
		String_t* L_0 = ___0_tag;
		int32_t L_1 = ___1_rewardId;
		int32_t L_2 = L_1;
		RuntimeObject* L_3 = Box(Int32_t680FF22E76F6EFAD4375103CBBFFA0421349384C_il2cpp_TypeInfo_var, &L_2);
		String_t* L_4;
		L_4 = String_Format_mFB7DA489BD99F4670881FF50EC017BFB0A5C0987(_stringLiteral8F9B51CCF3AF3FFAF9266651B8748CEC7B6E3F23, L_0, L_3, NULL);
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(L_4, NULL);
		// }
		return;
	}
}
// System.Boolean RiseSdk.RiseSdkForEditor::GetAdsEnabledState()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool RiseSdkForEditor_GetAdsEnabledState_m58A52D505E366D2A6138E5076B1559502EA0F71C (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, const RuntimeMethod* method) 
{
	{
		// return _isAdsEnabled;
		bool L_0 = __this->____isAdsEnabled_0;
		return L_0;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::SetAdsEnabledState(System.Boolean)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_SetAdsEnabledState_mA3D6136E5007ABC345BC7623AF363CD6E837D416 (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, bool ___0_state, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral22E9F4C83DA6C833585AF2FF7338762737D7DF2F);
		s_Il2CppMethodInitialized = true;
	}
	{
		// _isAdsEnabled = state;
		bool L_0 = ___0_state;
		__this->____isAdsEnabled_0 = L_0;
		// Debug.Log($"Editor set ads enabled state to {state}");
		bool L_1 = ___0_state;
		bool L_2 = L_1;
		RuntimeObject* L_3 = Box(Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var, &L_2);
		String_t* L_4;
		L_4 = String_Format_mA8DBB4C2516B9723C5A41E6CB1E2FAF4BBE96DD8(_stringLiteral22E9F4C83DA6C833585AF2FF7338762737D7DF2F, L_3, NULL);
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(L_4, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::ToastMsg(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor_ToastMsg_mC3878B09B0B37D516025A41E32B2F4DBBB9CF165 (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, String_t* ___0_str, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral4B2613D099EF787543CE5B1BEFD1E9EA92363726);
		s_Il2CppMethodInitialized = true;
	}
	{
		// Debug.Log($"Editor show toast msg:{str}");
		String_t* L_0 = ___0_str;
		String_t* L_1;
		L_1 = String_Concat_m9E3155FB84015C823606188F53B47CB44C444991(_stringLiteral4B2613D099EF787543CE5B1BEFD1E9EA92363726, L_0, NULL);
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(L_1, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForEditor::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForEditor__ctor_m07B6B55E9AC10ACCE362AA8BD03D2B20345BE8C2 (RiseSdkForEditor_tE10477A023276A61C4AE8805B1F878C40A8B28D3* __this, const RuntimeMethod* method) 
{
	{
		// private bool _isAdsEnabled = true;
		__this->____isAdsEnabled_0 = (bool)1;
		IRiseSdk__ctor_m1B1BAD2005EB2D6AD68784DDE4E93173E5802F2B(__this, NULL);
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdkForIOS::OnInit()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_OnInit_mDF7543DDA52B24F008D42F93E25B14457107EB5A (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::OnPause()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_OnPause_m9296D5A8BFD19860BD32EAE871D2813315369B44 (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::OnResume()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_OnResume_mD8985238754C016787BBC02FBACDF3F01CA18572 (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::OnStart()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_OnStart_m740E66136CFC902602DC881C05C1F55BFD366DCB (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::OnStop()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_OnStop_m5E08B4A13668C2355B531A8EF1A2CEB50AB8292C (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::FireBaseTrackEvent(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_FireBaseTrackEvent_m542EE2A101D1E07DAD9A9A1391B6FE56656428BE (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, String_t* ___0_category, String_t* ___1_keyValueData, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::recordException(System.String,System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_recordException_m25132C9A8F7DF6D8BC201513068F5F9E6A611757 (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, String_t* ___0_title, String_t* ___1_err, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Boolean RiseSdk.RiseSdkForIOS::HasRewardAd()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool RiseSdkForIOS_HasRewardAd_mAFE1AC2EE0000A5BAD3A4227A3C4B35E68781A32 (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, const RuntimeMethod* method) 
{
	{
		// return false;
		return (bool)0;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::ShowRewardAd(System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_ShowRewardAd_mF74442D039E61A47EFE1CEEA43904E6EC4073B3D (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, int32_t ___0_rewardId, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::ShowRewardAd(System.String,System.Int32)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_ShowRewardAd_m3348B1F1FD81327CEA18D87F0DAF48269370C4F8 (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, String_t* ___0_tag, int32_t ___1_rewardId, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Boolean RiseSdk.RiseSdkForIOS::GetAdsEnabledState()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR bool RiseSdkForIOS_GetAdsEnabledState_m8C25287BB92063F88A7DFB8565FD060DCABA957D (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, const RuntimeMethod* method) 
{
	{
		// return false;
		return (bool)0;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::SetAdsEnabledState(System.Boolean)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_SetAdsEnabledState_m2AD4435FAA97602D2AEBD414E17A3B342ABC0D1F (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, bool ___0_state, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0076CD8842170AB6C7A6A146C450B493C7365FCE);
		s_Il2CppMethodInitialized = true;
	}
	{
		// Debug.Log($"IOS set ads enabled state: {state}");
		bool L_0 = ___0_state;
		bool L_1 = L_0;
		RuntimeObject* L_2 = Box(Boolean_t09A6377A54BE2F9E6985A8149F19234FD7DDFE22_il2cpp_TypeInfo_var, &L_1);
		String_t* L_3;
		L_3 = String_Format_mA8DBB4C2516B9723C5A41E6CB1E2FAF4BBE96DD8(_stringLiteral0076CD8842170AB6C7A6A146C450B493C7365FCE, L_2, NULL);
		il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		Debug_Log_m87A9A3C761FF5C43ED8A53B16190A53D08F818BB(L_3, NULL);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::ToastMsg(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS_ToastMsg_m8BB5462C72ECF88B6F2E5F0E218B7D978DD00183 (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, String_t* ___0_str, const RuntimeMethod* method) 
{
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkForIOS::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkForIOS__ctor_mDDB44885863B5EAA935D8FA60215BF82FF7D4232 (RiseSdkForIOS_t6E4957EE2B87FFDD0A4B0F082C58D87F35072A27* __this, const RuntimeMethod* method) 
{
	{
		IRiseSdk__ctor_m1B1BAD2005EB2D6AD68784DDE4E93173E5802F2B(__this, NULL);
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Winvalid-offsetof"
#pragma clang diagnostic ignored "-Wunused-variable"
#endif
// System.Void RiseSdk.RiseSdkListener::add_OnPaymentEvent(System.Action`2<RiseSdk.RiseSdk/PaymentResult,System.Int32>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnPaymentEvent_m53A331279B826F1A3422189BA79E3FDAE14C1037 (Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* V_0 = NULL;
	Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* V_1 = NULL;
	Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* V_2 = NULL;
	{
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_1 = V_0;
		V_1 = L_1;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_2 = V_1;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F*)Castclass((RuntimeObject*)L_4, Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F_il2cpp_TypeInfo_var));
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_5 = V_2;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_6 = V_1;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4), L_5, L_6);
		V_0 = L_7;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_8 = V_0;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F*)L_8) == ((RuntimeObject*)(Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnPaymentEvent(System.Action`2<RiseSdk.RiseSdk/PaymentResult,System.Int32>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnPaymentEvent_m151D26320BD29047A0DBF99000EB474EC4C7DA05 (Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* V_0 = NULL;
	Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* V_1 = NULL;
	Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* V_2 = NULL;
	{
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_1 = V_0;
		V_1 = L_1;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_2 = V_1;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F*)Castclass((RuntimeObject*)L_4, Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F_il2cpp_TypeInfo_var));
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_5 = V_2;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_6 = V_1;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4), L_5, L_6);
		V_0 = L_7;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_8 = V_0;
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F*)L_8) == ((RuntimeObject*)(Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnSNSEvent(System.Action`2<RiseSdk.RiseSdk/SnsEventType,System.Int32>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnSNSEvent_m33AC755A0B498323C31EC1E3D1E663E200CE2E87 (Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* V_0 = NULL;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* V_1 = NULL;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* V_2 = NULL;
	{
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_1 = V_0;
		V_1 = L_1;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_2 = V_1;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6*)Castclass((RuntimeObject*)L_4, Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6_il2cpp_TypeInfo_var));
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_5 = V_2;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_6 = V_1;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5), L_5, L_6);
		V_0 = L_7;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_8 = V_0;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6*)L_8) == ((RuntimeObject*)(Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnSNSEvent(System.Action`2<RiseSdk.RiseSdk/SnsEventType,System.Int32>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnSNSEvent_m757A1ED923004BADCE5E45139F31CA162F97C265 (Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* V_0 = NULL;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* V_1 = NULL;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* V_2 = NULL;
	{
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_1 = V_0;
		V_1 = L_1;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_2 = V_1;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6*)Castclass((RuntimeObject*)L_4, Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6_il2cpp_TypeInfo_var));
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_5 = V_2;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_6 = V_1;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5), L_5, L_6);
		V_0 = L_7;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_8 = V_0;
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6*)L_8) == ((RuntimeObject*)(Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnCacheUrlResult(System.Action`3<System.Boolean,System.Int32,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnCacheUrlResult_m1FFB86D0050CD46A11CBEF54C47100725225CF72 (Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* V_0 = NULL;
	Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* V_1 = NULL;
	Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* V_2 = NULL;
	{
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnCacheUrlResult_6;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_1 = V_0;
		V_1 = L_1;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_2 = V_1;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72*)Castclass((RuntimeObject*)L_4, Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72_il2cpp_TypeInfo_var));
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_5 = V_2;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_6 = V_1;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnCacheUrlResult_6), L_5, L_6);
		V_0 = L_7;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_8 = V_0;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72*)L_8) == ((RuntimeObject*)(Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnCacheUrlResult(System.Action`3<System.Boolean,System.Int32,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnCacheUrlResult_m49B835E6DEB710A30EADEFC9AC02B2F65B2639EB (Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* V_0 = NULL;
	Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* V_1 = NULL;
	Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* V_2 = NULL;
	{
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnCacheUrlResult_6;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_1 = V_0;
		V_1 = L_1;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_2 = V_1;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72*)Castclass((RuntimeObject*)L_4, Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72_il2cpp_TypeInfo_var));
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_5 = V_2;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_6 = V_1;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnCacheUrlResult_6), L_5, L_6);
		V_0 = L_7;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_8 = V_0;
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72*)L_8) == ((RuntimeObject*)(Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnLeaderBoardEvent(System.Action`4<System.Boolean,System.Boolean,System.String,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnLeaderBoardEvent_mCCACCAD033A1156635B5373C6D394AC7803C4E38 (Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* V_0 = NULL;
	Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* V_1 = NULL;
	Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* V_2 = NULL;
	{
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_1 = V_0;
		V_1 = L_1;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_2 = V_1;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA*)Castclass((RuntimeObject*)L_4, Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA_il2cpp_TypeInfo_var));
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_5 = V_2;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_6 = V_1;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7), L_5, L_6);
		V_0 = L_7;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_8 = V_0;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA*)L_8) == ((RuntimeObject*)(Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnLeaderBoardEvent(System.Action`4<System.Boolean,System.Boolean,System.String,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnLeaderBoardEvent_m2AC682F635B004CD2C6B11C9830E271EFECD9197 (Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* V_0 = NULL;
	Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* V_1 = NULL;
	Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* V_2 = NULL;
	{
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_1 = V_0;
		V_1 = L_1;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_2 = V_1;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA*)Castclass((RuntimeObject*)L_4, Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA_il2cpp_TypeInfo_var));
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_5 = V_2;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_6 = V_1;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7), L_5, L_6);
		V_0 = L_7;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_8 = V_0;
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA*)L_8) == ((RuntimeObject*)(Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnReceiveServerResult(System.Action`3<System.Int32,System.Boolean,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnReceiveServerResult_m5FED9EC7092E0728C7FB067319A7F6A7A0442FD0 (Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* V_0 = NULL;
	Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* V_1 = NULL;
	Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* V_2 = NULL;
	{
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerResult_8;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_1 = V_0;
		V_1 = L_1;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_2 = V_1;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD*)Castclass((RuntimeObject*)L_4, Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD_il2cpp_TypeInfo_var));
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_5 = V_2;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_6 = V_1;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerResult_8), L_5, L_6);
		V_0 = L_7;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_8 = V_0;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD*)L_8) == ((RuntimeObject*)(Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnReceiveServerResult(System.Action`3<System.Int32,System.Boolean,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnReceiveServerResult_m080CCF71179DDBD6C74EC0920EAA7D2351ED6366 (Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* V_0 = NULL;
	Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* V_1 = NULL;
	Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* V_2 = NULL;
	{
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerResult_8;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_1 = V_0;
		V_1 = L_1;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_2 = V_1;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD*)Castclass((RuntimeObject*)L_4, Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD_il2cpp_TypeInfo_var));
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_5 = V_2;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_6 = V_1;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerResult_8), L_5, L_6);
		V_0 = L_7;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_8 = V_0;
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD*)L_8) == ((RuntimeObject*)(Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnReceivePaymentsPrice(System.Action`1<System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnReceivePaymentsPrice_m0792C09667E5248A56E5DEE2AAB3DB1CA097785B (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_0 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_1 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_2 = NULL;
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceivePaymentsPrice_9;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = V_0;
		V_1 = L_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)Castclass((RuntimeObject*)L_4, Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var));
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_5 = V_2;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_6 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceivePaymentsPrice_9), L_5, L_6);
		V_0 = L_7;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_8 = V_0;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_8) == ((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnReceivePaymentsPrice(System.Action`1<System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnReceivePaymentsPrice_mCBEAB41101336BF3EF743414994B9B77D58A838D (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_0 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_1 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_2 = NULL;
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceivePaymentsPrice_9;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = V_0;
		V_1 = L_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)Castclass((RuntimeObject*)L_4, Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var));
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_5 = V_2;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_6 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceivePaymentsPrice_9), L_5, L_6);
		V_0 = L_7;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_8 = V_0;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_8) == ((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnReceiveServerExtra(System.Action`1<System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnReceiveServerExtra_m0914EA81A064E4E07464D07E7C85310D49EC19C8 (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_0 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_1 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_2 = NULL;
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerExtra_10;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = V_0;
		V_1 = L_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)Castclass((RuntimeObject*)L_4, Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var));
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_5 = V_2;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_6 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerExtra_10), L_5, L_6);
		V_0 = L_7;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_8 = V_0;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_8) == ((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnReceiveServerExtra(System.Action`1<System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnReceiveServerExtra_m909E22F4895204E1CA6FBA8E247238D2E9FF6667 (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_0 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_1 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_2 = NULL;
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerExtra_10;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = V_0;
		V_1 = L_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)Castclass((RuntimeObject*)L_4, Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var));
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_5 = V_2;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_6 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerExtra_10), L_5, L_6);
		V_0 = L_7;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_8 = V_0;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_8) == ((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnReceiveNotificationData(System.Action`1<System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnReceiveNotificationData_mF4648EFBBC0D8F8E6269B73ECD41F13E45652CD5 (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_0 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_1 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_2 = NULL;
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveNotificationData_11;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = V_0;
		V_1 = L_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)Castclass((RuntimeObject*)L_4, Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var));
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_5 = V_2;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_6 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveNotificationData_11), L_5, L_6);
		V_0 = L_7;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_8 = V_0;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_8) == ((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnReceiveNotificationData(System.Action`1<System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnReceiveNotificationData_mA6738BE6DB4E105DBA5C37B8D7C1CA39E8C1F4D7 (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_0 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_1 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_2 = NULL;
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveNotificationData_11;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = V_0;
		V_1 = L_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)Castclass((RuntimeObject*)L_4, Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var));
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_5 = V_2;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_6 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveNotificationData_11), L_5, L_6);
		V_0 = L_7;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_8 = V_0;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_8) == ((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnDeliciousIconClickEvent(System.Action`2<System.String,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnDeliciousIconClickEvent_mF2CF22C6BDDA6CCC7A8A7F2759DEB167EA960C70 (Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* V_0 = NULL;
	Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* V_1 = NULL;
	Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* V_2 = NULL;
	{
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnDeliciousIconClickEvent_12;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_1 = V_0;
		V_1 = L_1;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_2 = V_1;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D*)Castclass((RuntimeObject*)L_4, Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D_il2cpp_TypeInfo_var));
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_5 = V_2;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_6 = V_1;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnDeliciousIconClickEvent_12), L_5, L_6);
		V_0 = L_7;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_8 = V_0;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D*)L_8) == ((RuntimeObject*)(Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnDeliciousIconClickEvent(System.Action`2<System.String,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnDeliciousIconClickEvent_m38C9EE3A3CB2858BC7BA6ED748C6EDC2A4CC1F7A (Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* V_0 = NULL;
	Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* V_1 = NULL;
	Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* V_2 = NULL;
	{
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnDeliciousIconClickEvent_12;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_1 = V_0;
		V_1 = L_1;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_2 = V_1;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D*)Castclass((RuntimeObject*)L_4, Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D_il2cpp_TypeInfo_var));
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_5 = V_2;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_6 = V_1;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnDeliciousIconClickEvent_12), L_5, L_6);
		V_0 = L_7;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_8 = V_0;
		Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D*)L_8) == ((RuntimeObject*)(Action_2_t3EDD987DFCD31953576008A0D7D4F44D8C984B1D*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_appLockEvent(System.Action`1<System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_appLockEvent_m2049D80784345B5A1FE9288103D553CC114FAE0B (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_0 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_1 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_2 = NULL;
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___appLockEvent_13;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = V_0;
		V_1 = L_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)Castclass((RuntimeObject*)L_4, Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var));
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_5 = V_2;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_6 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___appLockEvent_13), L_5, L_6);
		V_0 = L_7;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_8 = V_0;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_8) == ((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_appLockEvent(System.Action`1<System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_appLockEvent_mE726107327FFF73A49356148B736B58B6CDC2289 (Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_0 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_1 = NULL;
	Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* V_2 = NULL;
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___appLockEvent_13;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = V_0;
		V_1 = L_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)Castclass((RuntimeObject*)L_4, Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A_il2cpp_TypeInfo_var));
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_5 = V_2;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_6 = V_1;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___appLockEvent_13), L_5, L_6);
		V_0 = L_7;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_8 = V_0;
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_8) == ((RuntimeObject*)(Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnAdEvent(System.Action`4<RiseSdk.RiseSdk/AdEventType,System.Int32,System.String,System.Int32>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnAdEvent_m694028DA6D1642329EE3FB79CD1DB33434159DC0 (Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* V_0 = NULL;
	Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* V_1 = NULL;
	Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* V_2 = NULL;
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = V_0;
		V_1 = L_1;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_2 = V_1;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5*)Castclass((RuntimeObject*)L_4, Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5_il2cpp_TypeInfo_var));
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_5 = V_2;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_6 = V_1;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14), L_5, L_6);
		V_0 = L_7;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_8 = V_0;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5*)L_8) == ((RuntimeObject*)(Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnAdEvent(System.Action`4<RiseSdk.RiseSdk/AdEventType,System.Int32,System.String,System.Int32>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnAdEvent_m21D7B67F13F89E1E726CCEBE3441F7E50356E142 (Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* V_0 = NULL;
	Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* V_1 = NULL;
	Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* V_2 = NULL;
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = V_0;
		V_1 = L_1;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_2 = V_1;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5*)Castclass((RuntimeObject*)L_4, Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5_il2cpp_TypeInfo_var));
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_5 = V_2;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_6 = V_1;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14), L_5, L_6);
		V_0 = L_7;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_8 = V_0;
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5*)L_8) == ((RuntimeObject*)(Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnResumeAdEvent(System.Action)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnResumeAdEvent_m6F15FF12FCABF1AEE5E5B204A601F5BEDD61900A (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* V_0 = NULL;
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* V_1 = NULL;
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* V_2 = NULL;
	{
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnResumeAdEvent_15;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_1 = V_0;
		V_1 = L_1;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_2 = V_1;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)CastclassSealed((RuntimeObject*)L_4, Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var));
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_5 = V_2;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_6 = V_1;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnResumeAdEvent_15), L_5, L_6);
		V_0 = L_7;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_8 = V_0;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)L_8) == ((RuntimeObject*)(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnResumeAdEvent(System.Action)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnResumeAdEvent_mD8B9727657FDB5F7F0D968AE6EEEFB0B0898A2A4 (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* V_0 = NULL;
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* V_1 = NULL;
	Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* V_2 = NULL;
	{
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnResumeAdEvent_15;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_1 = V_0;
		V_1 = L_1;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_2 = V_1;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)CastclassSealed((RuntimeObject*)L_4, Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07_il2cpp_TypeInfo_var));
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_5 = V_2;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_6 = V_1;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnResumeAdEvent_15), L_5, L_6);
		V_0 = L_7;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_8 = V_0;
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)L_8) == ((RuntimeObject*)(Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnGMSEvent(System.Action`2<RiseSdk.RiseSdk/GMSEventType,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnGMSEvent_mEF6226DDA24190E96F94C63C194C7F9916E30F8A (Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* V_0 = NULL;
	Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* V_1 = NULL;
	Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* V_2 = NULL;
	{
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnGMSEvent_16;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_1 = V_0;
		V_1 = L_1;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_2 = V_1;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1*)Castclass((RuntimeObject*)L_4, Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1_il2cpp_TypeInfo_var));
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_5 = V_2;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_6 = V_1;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnGMSEvent_16), L_5, L_6);
		V_0 = L_7;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_8 = V_0;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1*)L_8) == ((RuntimeObject*)(Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnGMSEvent(System.Action`2<RiseSdk.RiseSdk/GMSEventType,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnGMSEvent_m3E407F4816B90C19EFCACA660BFB209BA488354B (Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* V_0 = NULL;
	Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* V_1 = NULL;
	Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* V_2 = NULL;
	{
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnGMSEvent_16;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_1 = V_0;
		V_1 = L_1;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_2 = V_1;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1*)Castclass((RuntimeObject*)L_4, Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1_il2cpp_TypeInfo_var));
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_5 = V_2;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_6 = V_1;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnGMSEvent_16), L_5, L_6);
		V_0 = L_7;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_8 = V_0;
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1*)L_8) == ((RuntimeObject*)(Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnAFEvent(System.Action`2<RiseSdk.RiseSdk/AFEventType,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnAFEvent_mD2732BFA0F88D9FE6921DD18AEC20D24F6EF86FA (Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* V_0 = NULL;
	Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* V_1 = NULL;
	Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* V_2 = NULL;
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_1 = V_0;
		V_1 = L_1;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_2 = V_1;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4*)Castclass((RuntimeObject*)L_4, Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4_il2cpp_TypeInfo_var));
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_5 = V_2;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_6 = V_1;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17), L_5, L_6);
		V_0 = L_7;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_8 = V_0;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4*)L_8) == ((RuntimeObject*)(Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnAFEvent(System.Action`2<RiseSdk.RiseSdk/AFEventType,System.String>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnAFEvent_mB52CA2004F503ABA0EB68FF552C677A1527A4A01 (Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* V_0 = NULL;
	Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* V_1 = NULL;
	Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* V_2 = NULL;
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_1 = V_0;
		V_1 = L_1;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_2 = V_1;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4*)Castclass((RuntimeObject*)L_4, Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4_il2cpp_TypeInfo_var));
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_5 = V_2;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_6 = V_1;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17), L_5, L_6);
		V_0 = L_7;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_8 = V_0;
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4*)L_8) == ((RuntimeObject*)(Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::add_OnXsollaLoginEvent(System.Action`1<System.Boolean>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_add_OnXsollaLoginEvent_m3192141517599AD3FA73C1E0B53535CC2161DAE4 (Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* V_0 = NULL;
	Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* V_1 = NULL;
	Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* V_2 = NULL;
	{
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnXsollaLoginEvent_18;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_1 = V_0;
		V_1 = L_1;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_2 = V_1;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Combine_m1F725AEF318BE6F0426863490691A6F4606E7D00(L_2, L_3, NULL);
		V_2 = ((Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C*)Castclass((RuntimeObject*)L_4, Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C_il2cpp_TypeInfo_var));
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_5 = V_2;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_6 = V_1;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnXsollaLoginEvent_18), L_5, L_6);
		V_0 = L_7;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_8 = V_0;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C*)L_8) == ((RuntimeObject*)(Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::remove_OnXsollaLoginEvent(System.Action`1<System.Boolean>)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_remove_OnXsollaLoginEvent_m302A5D7AB0ED0AC2ED26E46B2C1AC1242DCB75C9 (Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* ___0_value, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* V_0 = NULL;
	Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* V_1 = NULL;
	Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* V_2 = NULL;
	{
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnXsollaLoginEvent_18;
		V_0 = L_0;
	}

IL_0006:
	{
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_1 = V_0;
		V_1 = L_1;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_2 = V_1;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_3 = ___0_value;
		Delegate_t* L_4;
		L_4 = Delegate_Remove_m8B7DD5661308FA972E23CA1CC3FC9CEB355504E3(L_2, L_3, NULL);
		V_2 = ((Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C*)Castclass((RuntimeObject*)L_4, Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C_il2cpp_TypeInfo_var));
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_5 = V_2;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_6 = V_1;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_7;
		L_7 = InterlockedCompareExchangeImpl<Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C*>((&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnXsollaLoginEvent_18), L_5, L_6);
		V_0 = L_7;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_8 = V_0;
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_9 = V_1;
		if ((!(((RuntimeObject*)(Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C*)L_8) == ((RuntimeObject*)(Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C*)L_9))))
		{
			goto IL_0006;
		}
	}
	{
		return;
	}
}
// RiseSdk.RiseSdkListener RiseSdk.RiseSdkListener::get_Instance()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* RiseSdkListener_get_Instance_m866C1EBF47DE935C0E793F917A8ECA725A4817AE (const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&GameObject_AddComponent_TisRiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_mBBD0626F5E8E30C9E5DE7AF8642C92E276F70A26_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&GameObject_t76FEDD663AB33C991A9C9A23129337651094216F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_0_0_0_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Type_t_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral91846EFA3E6ED5AF1BC2957F86BFD3C16A8ACEA2);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (_instance == null)
		RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->____instance_19;
		il2cpp_codegen_runtime_class_init_inline(Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C_il2cpp_TypeInfo_var);
		bool L_1;
		L_1 = Object_op_Equality_mB6120F782D83091EF56A198FCEBCF066DB4A9605(L_0, (Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C*)NULL, NULL);
		if (!L_1)
		{
			goto IL_004d;
		}
	}
	{
		// _instance = FindObjectOfType(typeof(RiseSdkListener)) as RiseSdkListener;
		RuntimeTypeHandle_t332A452B8B6179E4469B69525D0FE82A88030F7B L_2 = { reinterpret_cast<intptr_t> (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_0_0_0_var) };
		il2cpp_codegen_runtime_class_init_inline(Type_t_il2cpp_TypeInfo_var);
		Type_t* L_3;
		L_3 = Type_GetTypeFromHandle_m6062B81682F79A4D6DF2640692EE6D9987858C57(L_2, NULL);
		il2cpp_codegen_runtime_class_init_inline(Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C_il2cpp_TypeInfo_var);
		Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C* L_4;
		L_4 = Object_FindObjectOfType_mECCF3627FE53B9B53B5D92BFB9FA88490B0ACD5E(L_3, NULL);
		((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->____instance_19 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1*)IsInstClass((RuntimeObject*)L_4, RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var));
		Il2CppCodeGenWriteBarrier((void**)(&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->____instance_19), (void*)((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1*)IsInstClass((RuntimeObject*)L_4, RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var)));
		// if (_instance == null)
		RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->____instance_19;
		bool L_6;
		L_6 = Object_op_Equality_mB6120F782D83091EF56A198FCEBCF066DB4A9605(L_5, (Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C*)NULL, NULL);
		if (!L_6)
		{
			goto IL_004d;
		}
	}
	{
		// GameObject obj = new GameObject("RiseSdkListener");
		GameObject_t76FEDD663AB33C991A9C9A23129337651094216F* L_7 = (GameObject_t76FEDD663AB33C991A9C9A23129337651094216F*)il2cpp_codegen_object_new(GameObject_t76FEDD663AB33C991A9C9A23129337651094216F_il2cpp_TypeInfo_var);
		NullCheck(L_7);
		GameObject__ctor_m37D512B05D292F954792225E6C6EEE95293A9B88(L_7, _stringLiteral91846EFA3E6ED5AF1BC2957F86BFD3C16A8ACEA2, NULL);
		// _instance = obj.AddComponent<RiseSdkListener>();
		GameObject_t76FEDD663AB33C991A9C9A23129337651094216F* L_8 = L_7;
		NullCheck(L_8);
		RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* L_9;
		L_9 = GameObject_AddComponent_TisRiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_mBBD0626F5E8E30C9E5DE7AF8642C92E276F70A26(L_8, GameObject_AddComponent_TisRiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_mBBD0626F5E8E30C9E5DE7AF8642C92E276F70A26_RuntimeMethod_var);
		((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->____instance_19 = L_9;
		Il2CppCodeGenWriteBarrier((void**)(&((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->____instance_19), (void*)L_9);
		// DontDestroyOnLoad(obj);
		il2cpp_codegen_runtime_class_init_inline(Object_tC12DECB6760A7F2CBF65D9DCF18D044C2D97152C_il2cpp_TypeInfo_var);
		Object_DontDestroyOnLoad_m4B70C3AEF886C176543D1295507B6455C9DCAEA7(L_8, NULL);
	}

IL_004d:
	{
		// return _instance;
		RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* L_10 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->____instance_19;
		return L_10;
	}
}
// System.Void RiseSdk.RiseSdkListener::OnApplicationPause(System.Boolean)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_OnApplicationPause_m5B764DAE108D51A016CABA5AB68D022DD6F82183 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, bool ___0_pauseStatus, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral63A7BC5625BD73B4EFFB4EB777EA75B79C73E722);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (pauseStatus)
		bool L_0 = ___0_pauseStatus;
		if (!L_0)
		{
			goto IL_0023;
		}
	}
	{
		// RiseSdk.Instance.OnPause();
		il2cpp_codegen_runtime_class_init_inline(RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* L_1;
		L_1 = RiseSdk_get_Instance_mF7B692D4BF44E697C87CCC5FA0ECCA2C846F54E2(NULL);
		NullCheck(L_1);
		VirtualActionInvoker0::Invoke(7 /* System.Void RiseSdk.IRiseSdk::OnPause() */, L_1);
		// if (appLockEvent != null)
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___appLockEvent_13;
		if (!L_2)
		{
			goto IL_0023;
		}
	}
	{
		// appLockEvent("pause");
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___appLockEvent_13;
		NullCheck(L_3);
		Action_1_Invoke_m690438AAE38F9762172E3AE0A33D0B42ACD35790_inline(L_3, _stringLiteral63A7BC5625BD73B4EFFB4EB777EA75B79C73E722, NULL);
	}

IL_0023:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::OnApplicationFocus(System.Boolean)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_OnApplicationFocus_m34156092EAAE70DA448A462A96178A4D30A78820 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, bool ___0_focusStatus, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0770C8A4CE7977C0CF704EEA0FE098E008D76600);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (focusStatus)
		bool L_0 = ___0_focusStatus;
		if (!L_0)
		{
			goto IL_0023;
		}
	}
	{
		// RiseSdk.Instance.OnResume();
		il2cpp_codegen_runtime_class_init_inline(RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* L_1;
		L_1 = RiseSdk_get_Instance_mF7B692D4BF44E697C87CCC5FA0ECCA2C846F54E2(NULL);
		NullCheck(L_1);
		VirtualActionInvoker0::Invoke(6 /* System.Void RiseSdk.IRiseSdk::OnResume() */, L_1);
		// if (appLockEvent != null)
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_2 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___appLockEvent_13;
		if (!L_2)
		{
			goto IL_0023;
		}
	}
	{
		// appLockEvent("focus");
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___appLockEvent_13;
		NullCheck(L_3);
		Action_1_Invoke_m690438AAE38F9762172E3AE0A33D0B42ACD35790_inline(L_3, _stringLiteral0770C8A4CE7977C0CF704EEA0FE098E008D76600, NULL);
	}

IL_0023:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::OnApplicationQuit()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_OnApplicationQuit_m856C4B4AAF1C1E838A53F0099B2E37E5A9D916D5 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// RiseSdk.Instance.OnStop();
		il2cpp_codegen_runtime_class_init_inline(RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* L_0;
		L_0 = RiseSdk_get_Instance_mF7B692D4BF44E697C87CCC5FA0ECCA2C846F54E2(NULL);
		NullCheck(L_0);
		VirtualActionInvoker0::Invoke(8 /* System.Void RiseSdk.IRiseSdk::OnStop() */, L_0);
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::Awake()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_Awake_mD2A2182D85E4987B8405424B83FECDAF575AC780 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// RiseSdk.Instance.OnStart();
		il2cpp_codegen_runtime_class_init_inline(RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F_il2cpp_TypeInfo_var);
		RiseSdk_tE6B5D17EB8471B27C55E5E71F7A07E0F0014132F* L_0;
		L_0 = RiseSdk_get_Instance_mF7B692D4BF44E697C87CCC5FA0ECCA2C846F54E2(NULL);
		NullCheck(L_0);
		VirtualActionInvoker0::Invoke(5 /* System.Void RiseSdk.IRiseSdk::OnStart() */, L_0);
		// }
		return;
	}
}
// System.Int32 RiseSdk.RiseSdkListener::E77ITgnNHHS(System.IntPtr,System.IntPtr)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR int32_t RiseSdkListener_E77ITgnNHHS_mBA8AA59A548404DD89A5584213E855A548B38DD4 (intptr_t ___0_ptr, intptr_t ___1_obj, const RuntimeMethod* method) 
{
	{
		// private static int E77ITgnNHHS(IntPtr ptr, IntPtr obj) { return 1; }
		return 1;
	}
}
// System.Void RiseSdk.RiseSdkListener::Update()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_Update_m7289B1A6BDC4D1292143949DD36A381299433E12 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (checkTimer < 100)
		float L_0 = __this->___checkTimer_20;
		if ((!(((float)L_0) < ((float)(100.0f)))))
		{
			goto IL_003e;
		}
	}
	{
		// checkTimer += Time.deltaTime;
		float L_1 = __this->___checkTimer_20;
		float L_2;
		L_2 = Time_get_deltaTime_mC3195000401F0FD167DD2F948FD2BC58330D0865(NULL);
		__this->___checkTimer_20 = ((float)il2cpp_codegen_add(L_1, L_2));
		// if (checkTimer > 30)
		float L_3 = __this->___checkTimer_20;
		if ((!(((float)L_3) > ((float)(30.0f)))))
		{
			goto IL_0081;
		}
	}
	{
		// checkTimer = 666;
		__this->___checkTimer_20 = (666.0f);
		// Check();
		RiseSdkListener_Check_m4E6C4AB847C12B0564AEB66D3E675A3116263132(__this, NULL);
		return;
	}

IL_003e:
	{
		// else if (checkTimer < 1000)
		float L_4 = __this->___checkTimer_20;
		if ((!(((float)L_4) < ((float)(1000.0f)))))
		{
			goto IL_0081;
		}
	}
	{
		// checkTimer += Time.deltaTime;
		float L_5 = __this->___checkTimer_20;
		float L_6;
		L_6 = Time_get_deltaTime_mC3195000401F0FD167DD2F948FD2BC58330D0865(NULL);
		__this->___checkTimer_20 = ((float)il2cpp_codegen_add(L_5, L_6));
		// if (checkTimer > 678)
		float L_7 = __this->___checkTimer_20;
		if ((!(((float)L_7) > ((float)(678.0f)))))
		{
			goto IL_0081;
		}
	}
	{
		// checkTimer = 6666;
		__this->___checkTimer_20 = (6666.0f);
		// if (!signatureCheckOk)
		bool L_8 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___signatureCheckOk_21;
		if (L_8)
		{
			goto IL_0081;
		}
	}
	{
		// Application.Quit();
		Application_Quit_mE304382DB9A6455C2A474C8F364C7387F37E9281(NULL);
	}

IL_0081:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::Check()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_Check_m4E6C4AB847C12B0564AEB66D3E675A3116263132 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaObject_Call_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_m020246E0988293B6126B690BD6CE4D894276AA3D_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&IntPtr_t_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral0A44B0E166277D89050FA0DBB49A6110AAC132EC);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral3753DF735E896BD3C9F2E5D3740D83C85D747CF9);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral4D613657609485AE586A3379BA0E3FC13C1E1078);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralE68E2F12E92A958E7B766A79531F38C5AE56AAFC);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralF1B4DDA1D30D45F9E87828D144D13ECAC3525591);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralFB4AE4F77150C3A8E8E4F8B23E734E0C7277B7D9);
		s_Il2CppMethodInitialized = true;
	}
	AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* V_0 = NULL;
	AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* V_1 = NULL;
	Exception_t* V_2 = NULL;
	il2cpp::utils::ExceptionSupportStack<RuntimeObject*, 2> __active_exceptions;
	try
	{// begin try (depth: 1)
		{
			// AndroidJavaClass unityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
			AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_0 = (AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03*)il2cpp_codegen_object_new(AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03_il2cpp_TypeInfo_var);
			NullCheck(L_0);
			AndroidJavaClass__ctor_mB5466169E1151B8CC44C8FED234D79984B431389(L_0, _stringLiteral4D613657609485AE586A3379BA0E3FC13C1E1078, NULL);
			V_0 = L_0;
			// if (unityClass == null)
			AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_1 = V_0;
			if (L_1)
			{
				goto IL_001d_1;
			}
		}
		{
			// Debug.LogError(":::UnityPlayer Class Is Null:::");
			il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
			Debug_LogError_mB00B2B4468EF3CAF041B038D840820FB84C924B2(_stringLiteralE68E2F12E92A958E7B766A79531F38C5AE56AAFC, NULL);
			// return;
			goto IL_00a8;
		}

IL_001d_1:
		{
			// AndroidJavaObject jObj = unityClass.GetStatic<AndroidJavaObject>("currentActivity");
			AndroidJavaClass_tE6296B30CC4BF84434A9B765267F3FD0DD8DDB03* L_2 = V_0;
			NullCheck(L_2);
			AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_3;
			L_3 = AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD(L_2, _stringLiteralFB4AE4F77150C3A8E8E4F8B23E734E0C7277B7D9, AndroidJavaObject_GetStatic_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_mD7D192A35EB2B2DA3775FAB081958B72088251DD_RuntimeMethod_var);
			V_1 = L_3;
			// if (jObj == null)
			AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_4 = V_1;
			if (L_4)
			{
				goto IL_0038_1;
			}
		}
		{
			// Debug.LogError(":::Unity Activity Class Is Null:::");
			il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
			Debug_LogError_mB00B2B4468EF3CAF041B038D840820FB84C924B2(_stringLiteral0A44B0E166277D89050FA0DBB49A6110AAC132EC, NULL);
			// return;
			goto IL_00a8;
		}

IL_0038_1:
		{
			// jObj = jObj.Call<AndroidJavaObject>("getApplicationContext");
			AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_5 = V_1;
			ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_6;
			L_6 = Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_inline(Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_RuntimeMethod_var);
			NullCheck(L_5);
			AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_7;
			L_7 = AndroidJavaObject_Call_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_m020246E0988293B6126B690BD6CE4D894276AA3D(L_5, _stringLiteral3753DF735E896BD3C9F2E5D3740D83C85D747CF9, L_6, AndroidJavaObject_Call_TisAndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0_m020246E0988293B6126B690BD6CE4D894276AA3D_RuntimeMethod_var);
			V_1 = L_7;
			// if (jObj == null)
			AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_8 = V_1;
			if (L_8)
			{
				goto IL_0058_1;
			}
		}
		{
			// Debug.LogError(":::Application Is Null:::");
			il2cpp_codegen_runtime_class_init_inline(Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var);
			Debug_LogError_mB00B2B4468EF3CAF041B038D840820FB84C924B2(_stringLiteralF1B4DDA1D30D45F9E87828D144D13ECAC3525591, NULL);
			// return;
			goto IL_00a8;
		}

IL_0058_1:
		{
		}
		try
		{// begin try (depth: 2)
			{
				// if (E77ITgnNHHS(IntPtr.Zero, jObj.GetRawObject()) != 1)
				intptr_t L_9 = ((IntPtr_t_StaticFields*)il2cpp_codegen_static_fields_for(IntPtr_t_il2cpp_TypeInfo_var))->___Zero_1;
				AndroidJavaObject_t8FFB930F335C1178405B82AC2BF512BB1EEF9EB0* L_10 = V_1;
				NullCheck(L_10);
				intptr_t L_11;
				L_11 = AndroidJavaObject_GetRawObject_m536F043B5CE2C21369FF6173C9D2A9A62136BC48(L_10, NULL);
				int32_t L_12;
				L_12 = RiseSdkListener_E77ITgnNHHS_mBA8AA59A548404DD89A5584213E855A548B38DD4(L_9, L_11, NULL);
				if ((((int32_t)L_12) == ((int32_t)1)))
				{
					goto IL_0073_2;
				}
			}
			{
				// Application.Quit();
				Application_Quit_mE304382DB9A6455C2A474C8F364C7387F37E9281(NULL);
				goto IL_0079_2;
			}

IL_0073_2:
			{
				// signatureCheckOk = true;
				((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___signatureCheckOk_21 = (bool)1;
			}

IL_0079_2:
			{
				// }
				goto IL_0083_1;
			}
		}// end try (depth: 2)
		catch(Il2CppExceptionWrapper& e)
		{
			if(il2cpp_codegen_class_is_assignable_from (((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&Exception_t_il2cpp_TypeInfo_var)), il2cpp_codegen_object_class(e.ex)))
			{
				IL2CPP_PUSH_ACTIVE_EXCEPTION(e.ex);
				goto CATCH_007b_1;
			}
			throw e;
		}

CATCH_007b_1:
		{// begin catch(System.Exception)
			// catch (System.Exception ex)
			// Application.Quit();
			Application_Quit_mE304382DB9A6455C2A474C8F364C7387F37E9281(NULL);
			// }
			IL2CPP_POP_ACTIVE_EXCEPTION();
			goto IL_0083_1;
		}// end catch (depth: 2)

IL_0083_1:
		{
			// }
			goto IL_00a8;
		}
	}// end try (depth: 1)
	catch(Il2CppExceptionWrapper& e)
	{
		if(il2cpp_codegen_class_is_assignable_from (((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&Exception_t_il2cpp_TypeInfo_var)), il2cpp_codegen_object_class(e.ex)))
		{
			IL2CPP_PUSH_ACTIVE_EXCEPTION(e.ex);
			goto CATCH_0085;
		}
		throw e;
	}

CATCH_0085:
	{// begin catch(System.Exception)
		// catch (System.Exception e)
		V_2 = ((Exception_t*)IL2CPP_GET_ACTIVE_EXCEPTION(Exception_t*));
		// Debug.LogError ("RiseSdkListener Check Error:::\n" + e.StackTrace + "\n" + e.Message);
		Exception_t* L_13 = V_2;
		NullCheck(L_13);
		String_t* L_14;
		L_14 = VirtualFuncInvoker0< String_t* >::Invoke(8 /* System.String System.Exception::get_StackTrace() */, L_13);
		Exception_t* L_15 = V_2;
		NullCheck(L_15);
		String_t* L_16;
		L_16 = VirtualFuncInvoker0< String_t* >::Invoke(5 /* System.String System.Exception::get_Message() */, L_15);
		String_t* L_17;
		L_17 = String_Concat_m093934F71A9B351911EE46311674ED463B180006(((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral130E17CA7453B93206D978925CBD030A85200E9C)), L_14, ((String_t*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&_stringLiteral00B28FF06B788B9B67C6B259800F404F9F3761FD)), L_16, NULL);
		il2cpp_codegen_runtime_class_init_inline(((RuntimeClass*)il2cpp_codegen_initialize_runtime_metadata_inline((uintptr_t*)&Debug_t8394C7EEAECA3689C2C9B9DE9C7166D73596276F_il2cpp_TypeInfo_var)));
		Debug_LogError_mB00B2B4468EF3CAF041B038D840820FB84C924B2(L_17, NULL);
		// }
		IL2CPP_POP_ACTIVE_EXCEPTION();
		goto IL_00a8;
	}// end catch (depth: 1)

IL_00a8:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::OnXsollaLoginState(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_OnXsollaLoginState_m3F4BF842554407AB5E7BCD2C2EB82BDA43130AE9 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralF944DCD635F9801F7AC90A407FBC479964DEC024);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnXsollaLoginEvent != null && OnXsollaLoginEvent.GetInvocationList().Length > 0)
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnXsollaLoginEvent_18;
		if (!L_0)
		{
			goto IL_0038;
		}
	}
	{
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnXsollaLoginEvent_18;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0038;
		}
	}
	{
		// if (data == "0")
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_op_Equality_m030E1B219352228970A076136E455C4E568C02C1(L_3, _stringLiteralF944DCD635F9801F7AC90A407FBC479964DEC024, NULL);
		if (!L_4)
		{
			goto IL_002d;
		}
	}
	{
		// OnXsollaLoginEvent(true);
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnXsollaLoginEvent_18;
		NullCheck(L_5);
		Action_1_Invoke_m69C8773D6967F3B224777183E24EA621CE056F8F_inline(L_5, (bool)1, NULL);
		return;
	}

IL_002d:
	{
		// OnXsollaLoginEvent(false);
		Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* L_6 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnXsollaLoginEvent_18;
		NullCheck(L_6);
		Action_1_Invoke_m69C8773D6967F3B224777183E24EA621CE056F8F_inline(L_6, (bool)0, NULL);
	}

IL_0038:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onGMSPaid(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onGMSPaid_m2A80D9795EC5B1D3F4CBA4D133C3A891350E5150 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnGMSEvent != null && OnGMSEvent.GetInvocationList().Length > 0)
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnGMSEvent_16;
		if (!L_0)
		{
			goto IL_0020;
		}
	}
	{
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnGMSEvent_16;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0020;
		}
	}
	{
		// OnGMSEvent(RiseSdk.GMSEventType.GMSPaid, data);
		Action_2_tF0627A2EA4DC88926B777ABA7B3043682CD316C1* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnGMSEvent_16;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_2_Invoke_m1706876FE6BBB886C80DF6E3C606854C525844F9_inline(L_3, 1, L_4, NULL);
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onAFInitSuccess(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onAFInitSuccess_m839698545A4E7DB87A56FE6EF2D9126415B67F08 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnAFEvent != null && OnAFEvent.GetInvocationList().Length > 0)
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		if (!L_0)
		{
			goto IL_0020;
		}
	}
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0020;
		}
	}
	{
		// OnAFEvent(RiseSdk.AFEventType.InitSuccess, data);
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_2_Invoke_mF6A889F5B1A87B716BC53950D781FA1708CB54A9_inline(L_3, 1, L_4, NULL);
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onAFInitFailed(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onAFInitFailed_m4EC15AEA2B22CF39AEE1A939940EE1ECB8B904B2 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnAFEvent != null && OnAFEvent.GetInvocationList().Length > 0)
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		if (!L_0)
		{
			goto IL_0020;
		}
	}
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0020;
		}
	}
	{
		// OnAFEvent(RiseSdk.AFEventType.InitFail, data);
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_2_Invoke_mF6A889F5B1A87B716BC53950D781FA1708CB54A9_inline(L_3, 2, L_4, NULL);
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onAFAppOpenAttribution(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onAFAppOpenAttribution_mB17FA78227887C6BC099B841D17E52AEE466056E (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnAFEvent != null && OnAFEvent.GetInvocationList().Length > 0)
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		if (!L_0)
		{
			goto IL_0020;
		}
	}
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0020;
		}
	}
	{
		// OnAFEvent(RiseSdk.AFEventType.AppOpenAttribution, data);
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_2_Invoke_mF6A889F5B1A87B716BC53950D781FA1708CB54A9_inline(L_3, 3, L_4, NULL);
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onAFConversionDataFail(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onAFConversionDataFail_m910EE4ABA88EC76A117CDAC60C7E0B97131A65C1 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnAFEvent != null && OnAFEvent.GetInvocationList().Length > 0)
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		if (!L_0)
		{
			goto IL_0020;
		}
	}
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0020;
		}
	}
	{
		// OnAFEvent(RiseSdk.AFEventType.ConversionDataFail, data);
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_2_Invoke_mF6A889F5B1A87B716BC53950D781FA1708CB54A9_inline(L_3, 6, L_4, NULL);
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onAFConversionDataSuccess(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onAFConversionDataSuccess_mCABDEB1A3403A2A7F217D83184E8E618EEC5D32E (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnAFEvent != null && OnAFEvent.GetInvocationList().Length > 0)
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		if (!L_0)
		{
			goto IL_0020;
		}
	}
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0020;
		}
	}
	{
		// OnAFEvent(RiseSdk.AFEventType.ConversionDataSuccess, data);
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_2_Invoke_mF6A889F5B1A87B716BC53950D781FA1708CB54A9_inline(L_3, 5, L_4, NULL);
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onAFAttributionFailure(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onAFAttributionFailure_m8EC740796E9F720430783DB8367DBC40F3E72DF1 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnAFEvent != null && OnAFEvent.GetInvocationList().Length > 0)
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		if (!L_0)
		{
			goto IL_0020;
		}
	}
	{
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0020;
		}
	}
	{
		// OnAFEvent(RiseSdk.AFEventType.AttributionFailure, data);
		Action_2_t22233A6F1527AF4EB633009AA4A2FB2E9D9F16A4* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAFEvent_17;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_2_Invoke_mF6A889F5B1A87B716BC53950D781FA1708CB54A9_inline(L_3, 4, L_4, NULL);
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::OnResumeAd()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_OnResumeAd_m880C07861575B66DACB42F2153217BD3A1088CB4 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnResumeAdEvent != null)
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnResumeAdEvent_15;
		if (!L_0)
		{
			goto IL_0011;
		}
	}
	{
		// OnResumeAdEvent();
		Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnResumeAdEvent_15;
		NullCheck(L_1);
		Action_Invoke_m7126A54DACA72B845424072887B5F3A51FC3808E_inline(L_1, NULL);
	}

IL_0011:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onPaymentSuccess(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onPaymentSuccess_m07CD6AE4C0843D898A8AB6A30A34EDB8711DF73C (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_billId, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	{
		// if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList().Length > 0)
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		if (!L_0)
		{
			goto IL_0027;
		}
	}
	{
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0027;
		}
	}
	{
		// int id = int.Parse(billId);
		String_t* L_3 = ___0_billId;
		int32_t L_4;
		L_4 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_3, NULL);
		V_0 = L_4;
		// OnPaymentEvent(RiseSdk.PaymentResult.Success, id);
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		int32_t L_6 = V_0;
		NullCheck(L_5);
		Action_2_Invoke_m1734278892DDE9FFC7FE67FAC65971B69808C74C_inline(L_5, 1, L_6, NULL);
	}

IL_0027:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onPaymentFail(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onPaymentFail_mB37F71783700D9E6511AEE7890C5CAA0AA6E9D97 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_billId, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	{
		// if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList().Length > 0)
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		if (!L_0)
		{
			goto IL_0027;
		}
	}
	{
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0027;
		}
	}
	{
		// int id = int.Parse(billId);
		String_t* L_3 = ___0_billId;
		int32_t L_4;
		L_4 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_3, NULL);
		V_0 = L_4;
		// OnPaymentEvent(RiseSdk.PaymentResult.Failed, id);
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		int32_t L_6 = V_0;
		NullCheck(L_5);
		Action_2_Invoke_m1734278892DDE9FFC7FE67FAC65971B69808C74C_inline(L_5, 2, L_6, NULL);
	}

IL_0027:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onPaymentCanceled(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onPaymentCanceled_m06CE7F1614E0D5E8E28DDCC1EBA6BC609C0236DB (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_billId, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	{
		// if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList().Length > 0)
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		if (!L_0)
		{
			goto IL_0027;
		}
	}
	{
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0027;
		}
	}
	{
		// int id = int.Parse(billId);
		String_t* L_3 = ___0_billId;
		int32_t L_4;
		L_4 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_3, NULL);
		V_0 = L_4;
		// OnPaymentEvent(RiseSdk.PaymentResult.Cancel, id);
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		int32_t L_6 = V_0;
		NullCheck(L_5);
		Action_2_Invoke_m1734278892DDE9FFC7FE67FAC65971B69808C74C_inline(L_5, 3, L_6, NULL);
	}

IL_0027:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onPaymentSystemError(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onPaymentSystemError_m8CA0A17D446CDC349A08A1EEDE77731C18720C7A (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList().Length > 0)
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		if (!L_0)
		{
			goto IL_0020;
		}
	}
	{
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0020;
		}
	}
	{
		// OnPaymentEvent(RiseSdk.PaymentResult.PaymentSystemError, -1);
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		NullCheck(L_3);
		Action_2_Invoke_m1734278892DDE9FFC7FE67FAC65971B69808C74C_inline(L_3, 4, (-1), NULL);
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onPaymentSystemValid(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onPaymentSystemValid_m071EC2B02324E7C38D15542C850A1D1743943B85 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList().Length > 0)
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		if (!L_0)
		{
			goto IL_0020;
		}
	}
	{
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0020;
		}
	}
	{
		// OnPaymentEvent(RiseSdk.PaymentResult.PaymentSystemValid, -1);
		Action_2_t62A33F8E1391101187E366EE1114E85FBEFABB1F* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnPaymentEvent_4;
		NullCheck(L_3);
		Action_2_Invoke_m1734278892DDE9FFC7FE67FAC65971B69808C74C_inline(L_3, 5, (-1), NULL);
	}

IL_0020:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onReceiveBillPrices(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onReceiveBillPrices_mAF947A6A420005AA5CF075F86EFD0EE88C812B0B (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnReceivePaymentsPrice != null && OnReceivePaymentsPrice.GetInvocationList().Length > 0)
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceivePaymentsPrice_9;
		if (!L_0)
		{
			goto IL_001f;
		}
	}
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceivePaymentsPrice_9;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_001f;
		}
	}
	{
		// OnReceivePaymentsPrice(data);
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceivePaymentsPrice_9;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_1_Invoke_m690438AAE38F9762172E3AE0A33D0B42ACD35790_inline(L_3, L_4, NULL);
	}

IL_001f:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onReceiveLoginResult(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onReceiveLoginResult_mCE321BA94A043EEF0FCBBBECF5D021DB10E751B0 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_result, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B4_0 = NULL;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B3_0 = NULL;
	int32_t G_B5_0 = 0;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B5_1 = NULL;
	{
		// if (OnSNSEvent != null && OnSNSEvent.GetInvocationList().Length > 0)
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		if (!L_0)
		{
			goto IL_002d;
		}
	}
	{
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_002d;
		}
	}
	{
		// int success = int.Parse(result);
		String_t* L_3 = ___0_result;
		int32_t L_4;
		L_4 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_3, NULL);
		V_0 = L_4;
		// OnSNSEvent(success == 0 ? RiseSdk.SnsEventType.LoginSuccess : RiseSdk.SnsEventType.LoginFailed, 0);
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		int32_t L_6 = V_0;
		G_B3_0 = L_5;
		if (!L_6)
		{
			G_B4_0 = L_5;
			goto IL_0026;
		}
	}
	{
		G_B5_0 = 2;
		G_B5_1 = G_B3_0;
		goto IL_0027;
	}

IL_0026:
	{
		G_B5_0 = 1;
		G_B5_1 = G_B4_0;
	}

IL_0027:
	{
		NullCheck(G_B5_1);
		Action_2_Invoke_m55AB25A539E14B5A8AB23805B6406481EF687FA7_inline(G_B5_1, G_B5_0, 0, NULL);
	}

IL_002d:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onReceiveInviteResult(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onReceiveInviteResult_m7AE403615E79FE039BC339CABC876AC3943E35E1 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_result, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B4_0 = NULL;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B3_0 = NULL;
	int32_t G_B5_0 = 0;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B5_1 = NULL;
	{
		// if (OnSNSEvent != null && OnSNSEvent.GetInvocationList().Length > 0)
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		if (!L_0)
		{
			goto IL_002d;
		}
	}
	{
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_002d;
		}
	}
	{
		// int success = int.Parse(result);
		String_t* L_3 = ___0_result;
		int32_t L_4;
		L_4 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_3, NULL);
		V_0 = L_4;
		// OnSNSEvent(success == 0 ? RiseSdk.SnsEventType.InviteSuccess : RiseSdk.SnsEventType.InviteFailed, 0);
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		int32_t L_6 = V_0;
		G_B3_0 = L_5;
		if (!L_6)
		{
			G_B4_0 = L_5;
			goto IL_0026;
		}
	}
	{
		G_B5_0 = 4;
		G_B5_1 = G_B3_0;
		goto IL_0027;
	}

IL_0026:
	{
		G_B5_0 = 3;
		G_B5_1 = G_B4_0;
	}

IL_0027:
	{
		NullCheck(G_B5_1);
		Action_2_Invoke_m55AB25A539E14B5A8AB23805B6406481EF687FA7_inline(G_B5_1, G_B5_0, 0, NULL);
	}

IL_002d:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onReceiveLikeResult(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onReceiveLikeResult_m64A5F5E03ADA232A1442434649B3D01461077435 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_result, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B4_0 = NULL;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B3_0 = NULL;
	int32_t G_B5_0 = 0;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B5_1 = NULL;
	{
		// if (OnSNSEvent != null && OnSNSEvent.GetInvocationList().Length > 0)
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		if (!L_0)
		{
			goto IL_002d;
		}
	}
	{
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_002d;
		}
	}
	{
		// int success = int.Parse(result);
		String_t* L_3 = ___0_result;
		int32_t L_4;
		L_4 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_3, NULL);
		V_0 = L_4;
		// OnSNSEvent(success == 0 ? RiseSdk.SnsEventType.LikeSuccess : RiseSdk.SnsEventType.LikeFailed, 0);
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		int32_t L_6 = V_0;
		G_B3_0 = L_5;
		if (!L_6)
		{
			G_B4_0 = L_5;
			goto IL_0026;
		}
	}
	{
		G_B5_0 = 8;
		G_B5_1 = G_B3_0;
		goto IL_0027;
	}

IL_0026:
	{
		G_B5_0 = 7;
		G_B5_1 = G_B4_0;
	}

IL_0027:
	{
		NullCheck(G_B5_1);
		Action_2_Invoke_m55AB25A539E14B5A8AB23805B6406481EF687FA7_inline(G_B5_1, G_B5_0, 0, NULL);
	}

IL_002d:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onReceiveChallengeResult(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onReceiveChallengeResult_m1C983D5A185F2A914DEC2471C2FE8136DF5D38F0 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_result, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	int32_t V_0 = 0;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B4_0 = NULL;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B3_0 = NULL;
	int32_t G_B5_0 = 0;
	Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* G_B5_1 = NULL;
	{
		// if (OnSNSEvent != null && OnSNSEvent.GetInvocationList().Length > 0)
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		if (!L_0)
		{
			goto IL_002e;
		}
	}
	{
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_002e;
		}
	}
	{
		// int count = int.Parse(result);
		String_t* L_3 = ___0_result;
		int32_t L_4;
		L_4 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_3, NULL);
		V_0 = L_4;
		// OnSNSEvent(count > 0 ? RiseSdk.SnsEventType.ChallengeSuccess : RiseSdk.SnsEventType.ChallengeFailed, count);
		Action_2_tAAB130971801DCCE66C761034BF0EB6453DF08C6* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnSNSEvent_5;
		int32_t L_6 = V_0;
		G_B3_0 = L_5;
		if ((((int32_t)L_6) > ((int32_t)0)))
		{
			G_B4_0 = L_5;
			goto IL_0027;
		}
	}
	{
		G_B5_0 = 6;
		G_B5_1 = G_B3_0;
		goto IL_0028;
	}

IL_0027:
	{
		G_B5_0 = 5;
		G_B5_1 = G_B4_0;
	}

IL_0028:
	{
		int32_t L_7 = V_0;
		NullCheck(G_B5_1);
		Action_2_Invoke_m55AB25A539E14B5A8AB23805B6406481EF687FA7_inline(G_B5_1, G_B5_0, L_7, NULL);
	}

IL_002e:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onSubmitSuccess(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onSubmitSuccess_mEDA51C626969CFB379D2463C0B7A05777815A17D (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_leaderBoardTag, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnLeaderBoardEvent != null && OnLeaderBoardEvent.GetInvocationList().Length > 0)
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		if (!L_0)
		{
			goto IL_0026;
		}
	}
	{
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0026;
		}
	}
	{
		// OnLeaderBoardEvent(true, true, leaderBoardTag, "");
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		String_t* L_4 = ___0_leaderBoardTag;
		NullCheck(L_3);
		Action_4_Invoke_m9D80EF97751363EE2BBBEDA48A47B537CAE8C3A5_inline(L_3, (bool)1, (bool)1, L_4, _stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709, NULL);
	}

IL_0026:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onSubmitFailure(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onSubmitFailure_m1709F03CBEE687EA7DF4156DCD5E1AA46AC91DDD (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_leaderBoardTag, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnLeaderBoardEvent != null && OnLeaderBoardEvent.GetInvocationList().Length > 0)
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		if (!L_0)
		{
			goto IL_0026;
		}
	}
	{
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0026;
		}
	}
	{
		// OnLeaderBoardEvent(true, false, leaderBoardTag, "");
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		String_t* L_4 = ___0_leaderBoardTag;
		NullCheck(L_3);
		Action_4_Invoke_m9D80EF97751363EE2BBBEDA48A47B537CAE8C3A5_inline(L_3, (bool)1, (bool)0, L_4, _stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709, NULL);
	}

IL_0026:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onLoadSuccess(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onLoadSuccess_mC3C9049F17DBF5DE46110B9F9FB92D3AB7FD85C0 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_0 = NULL;
	{
		// if (OnLeaderBoardEvent != null && OnLeaderBoardEvent.GetInvocationList().Length > 0)
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		if (!L_0)
		{
			goto IL_0030;
		}
	}
	{
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0030;
		}
	}
	{
		// string[] results = data.Split('|');
		String_t* L_3 = ___0_data;
		NullCheck(L_3);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_4;
		L_4 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_3, ((int32_t)124), 0, NULL);
		V_0 = L_4;
		// OnLeaderBoardEvent(false, true, results[0], results[1]);
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_5 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6 = V_0;
		NullCheck(L_6);
		int32_t L_7 = 0;
		String_t* L_8 = (L_6)->GetAt(static_cast<il2cpp_array_size_t>(L_7));
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_0;
		NullCheck(L_9);
		int32_t L_10 = 1;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		NullCheck(L_5);
		Action_4_Invoke_m9D80EF97751363EE2BBBEDA48A47B537CAE8C3A5_inline(L_5, (bool)0, (bool)1, L_8, L_11, NULL);
	}

IL_0030:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onLoadFailure(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onLoadFailure_m06D08A99EEFC056FCBCA040932A069B578E58AB1 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_leaderBoardTag, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnLeaderBoardEvent != null && OnLeaderBoardEvent.GetInvocationList().Length > 0)
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		if (!L_0)
		{
			goto IL_0026;
		}
	}
	{
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0026;
		}
	}
	{
		// OnLeaderBoardEvent(false, false, leaderBoardTag, "");
		Action_4_t37E3E14502E5ECCD0E39D257BD06B5AAFDEDF7DA* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnLeaderBoardEvent_7;
		String_t* L_4 = ___0_leaderBoardTag;
		NullCheck(L_3);
		Action_4_Invoke_m9D80EF97751363EE2BBBEDA48A47B537CAE8C3A5_inline(L_3, (bool)0, (bool)0, L_4, _stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709, NULL);
	}

IL_0026:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onServerResult(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onServerResult_m3348BF8927365323D8FABD174B36A7BC79A7D1CE (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_0 = NULL;
	int32_t V_1 = 0;
	bool V_2 = false;
	{
		// if (OnReceiveServerResult != null && OnReceiveServerResult.GetInvocationList().Length > 0)
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerResult_8;
		if (!L_0)
		{
			goto IL_0042;
		}
	}
	{
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerResult_8;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0042;
		}
	}
	{
		// string[] results = data.Split('|');
		String_t* L_3 = ___0_data;
		NullCheck(L_3);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_4;
		L_4 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_3, ((int32_t)124), 0, NULL);
		V_0 = L_4;
		// int resultCode = int.Parse(results[0]);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_5 = V_0;
		NullCheck(L_5);
		int32_t L_6 = 0;
		String_t* L_7 = (L_5)->GetAt(static_cast<il2cpp_array_size_t>(L_6));
		int32_t L_8;
		L_8 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_7, NULL);
		V_1 = L_8;
		// bool success = int.Parse(results[1]) == 0;
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_0;
		NullCheck(L_9);
		int32_t L_10 = 1;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		int32_t L_12;
		L_12 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_11, NULL);
		V_2 = (bool)((((int32_t)L_12) == ((int32_t)0))? 1 : 0);
		// OnReceiveServerResult(resultCode, success, results[2]);
		Action_3_t3220820FADC1E2D78105AA2360DAE058885822BD* L_13 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerResult_8;
		int32_t L_14 = V_1;
		bool L_15 = V_2;
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_16 = V_0;
		NullCheck(L_16);
		int32_t L_17 = 2;
		String_t* L_18 = (L_16)->GetAt(static_cast<il2cpp_array_size_t>(L_17));
		NullCheck(L_13);
		Action_3_Invoke_m1A8B27464F12A8FBBCB0D6B556E9C42DBCB33263_inline(L_13, L_14, L_15, L_18, NULL);
	}

IL_0042:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onCacheUrlResult(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onCacheUrlResult_m3CA40FA9716024F19AD84BFBD42F27048CAE6CF9 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709);
		s_Il2CppMethodInitialized = true;
	}
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_0 = NULL;
	int32_t V_1 = 0;
	{
		// if (OnCacheUrlResult != null && OnCacheUrlResult.GetInvocationList().Length > 0)
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnCacheUrlResult_6;
		if (!L_0)
		{
			goto IL_0055;
		}
	}
	{
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnCacheUrlResult_6;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0055;
		}
	}
	{
		// string[] results = data.Split('|');
		String_t* L_3 = ___0_data;
		NullCheck(L_3);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_4;
		L_4 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_3, ((int32_t)124), 0, NULL);
		V_0 = L_4;
		// int tag = int.Parse(results[0]);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_5 = V_0;
		NullCheck(L_5);
		int32_t L_6 = 0;
		String_t* L_7 = (L_5)->GetAt(static_cast<il2cpp_array_size_t>(L_6));
		int32_t L_8;
		L_8 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_7, NULL);
		V_1 = L_8;
		// bool success = int.Parse(results[1]) == 0;
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_0;
		NullCheck(L_9);
		int32_t L_10 = 1;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		int32_t L_12;
		L_12 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_11, NULL);
		// if (success)
		if (!((((int32_t)L_12) == ((int32_t)0))? 1 : 0))
		{
			goto IL_0044;
		}
	}
	{
		// OnCacheUrlResult(true, tag, results[2]);
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_13 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnCacheUrlResult_6;
		int32_t L_14 = V_1;
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_15 = V_0;
		NullCheck(L_15);
		int32_t L_16 = 2;
		String_t* L_17 = (L_15)->GetAt(static_cast<il2cpp_array_size_t>(L_16));
		NullCheck(L_13);
		Action_3_Invoke_m397F612018E5E6EA02DEBA300CDA0E0E3B3FC8EB_inline(L_13, (bool)1, L_14, L_17, NULL);
		return;
	}

IL_0044:
	{
		// OnCacheUrlResult(false, tag, "");
		Action_3_t040A9C46B3BE7A1E561150B4439FECC488D84E72* L_18 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnCacheUrlResult_6;
		int32_t L_19 = V_1;
		NullCheck(L_18);
		Action_3_Invoke_m397F612018E5E6EA02DEBA300CDA0E0E3B3FC8EB_inline(L_18, (bool)0, L_19, _stringLiteralDA39A3EE5E6B4B0D3255BFEF95601890AFD80709, NULL);
	}

IL_0055:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onReceiveServerExtra(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onReceiveServerExtra_m50B0A076EB733E5A9675E2C1601EBA6E3696B84F (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnReceiveServerExtra != null && OnReceiveServerExtra.GetInvocationList().Length > 0)
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerExtra_10;
		if (!L_0)
		{
			goto IL_001f;
		}
	}
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerExtra_10;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_001f;
		}
	}
	{
		// OnReceiveServerExtra(data);
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveServerExtra_10;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_1_Invoke_m690438AAE38F9762172E3AE0A33D0B42ACD35790_inline(L_3, L_4, NULL);
	}

IL_001f:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onReceiveNotificationData(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onReceiveNotificationData_mA85936DB7BF0E10163B269A45E3475F307C0786A (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		s_Il2CppMethodInitialized = true;
	}
	{
		// if (OnReceiveNotificationData != null && OnReceiveNotificationData.GetInvocationList().Length > 0)
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveNotificationData_11;
		if (!L_0)
		{
			goto IL_001f;
		}
	}
	{
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveNotificationData_11;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_001f;
		}
	}
	{
		// OnReceiveNotificationData(data);
		Action_1_t3CB5D1A819C3ED3F99E9E39F890F18633253949A* L_3 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnReceiveNotificationData_11;
		String_t* L_4 = ___0_data;
		NullCheck(L_3);
		Action_1_Invoke_m690438AAE38F9762172E3AE0A33D0B42ACD35790_inline(L_3, L_4, NULL);
	}

IL_001f:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onReceiveReward(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onReceiveReward_m3FE96CAB1CDE5C7B16263FCC112B518812694F08 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E);
		s_Il2CppMethodInitialized = true;
	}
	bool V_0 = false;
	int32_t V_1 = 0;
	String_t* V_2 = NULL;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_3 = NULL;
	{
		// if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		if (!L_0)
		{
			goto IL_008a;
		}
	}
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_008a;
		}
	}
	{
		// bool success = false;
		V_0 = (bool)0;
		// int id = -1;
		V_1 = (-1);
		// string tag = "Default";
		V_2 = _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
		// if (!string.IsNullOrEmpty(data))
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478(L_3, NULL);
		if (L_4)
		{
			goto IL_006a;
		}
	}
	{
		// string[] results = data.Split('|');
		String_t* L_5 = ___0_data;
		NullCheck(L_5);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6;
		L_6 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_5, ((int32_t)124), 0, NULL);
		V_3 = L_6;
		// if (results != null && results.Length > 1)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_7 = V_3;
		if (!L_7)
		{
			goto IL_006a;
		}
	}
	{
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_8 = V_3;
		NullCheck(L_8);
		if ((((int32_t)((int32_t)(((RuntimeArray*)L_8)->max_length))) <= ((int32_t)1)))
		{
			goto IL_006a;
		}
	}
	{
		// success = int.Parse(results[0]) == 0;
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_3;
		NullCheck(L_9);
		int32_t L_10 = 0;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		int32_t L_12;
		L_12 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_11, NULL);
		V_0 = (bool)((((int32_t)L_12) == ((int32_t)0))? 1 : 0);
		// id = int.Parse(results[1]);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_13 = V_3;
		NullCheck(L_13);
		int32_t L_14 = 1;
		String_t* L_15 = (L_13)->GetAt(static_cast<il2cpp_array_size_t>(L_14));
		int32_t L_16;
		L_16 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_15, NULL);
		V_1 = L_16;
		// if (results.Length > 2)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_17 = V_3;
		NullCheck(L_17);
		if ((((int32_t)((int32_t)(((RuntimeArray*)L_17)->max_length))) <= ((int32_t)2)))
		{
			goto IL_006a;
		}
	}
	{
		// tag = results[2];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_18 = V_3;
		NullCheck(L_18);
		int32_t L_19 = 2;
		String_t* L_20 = (L_18)->GetAt(static_cast<il2cpp_array_size_t>(L_19));
		V_2 = L_20;
		// if (results.Length > 3)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_21 = V_3;
		NullCheck(L_21);
		if ((((int32_t)((int32_t)(((RuntimeArray*)L_21)->max_length))) <= ((int32_t)3)))
		{
			goto IL_006a;
		}
	}
	{
		// skippedVideo = int.Parse(results[3]) == 0 ? true : false;
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_22 = V_3;
		NullCheck(L_22);
		int32_t L_23 = 3;
		String_t* L_24 = (L_22)->GetAt(static_cast<il2cpp_array_size_t>(L_23));
		int32_t L_25;
		L_25 = Int32_Parse_m273CA1A9C7717C99641291A95C543711C0202AF0(L_24, NULL);
	}

IL_006a:
	{
		// if (success)
		bool L_26 = V_0;
		if (!L_26)
		{
			goto IL_007c;
		}
	}
	{
		// OnAdEvent(RiseSdk.AdEventType.RewardAdShowFinished, id, tag, RiseSdk.ADTYPE_VIDEO);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_27 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		int32_t L_28 = V_1;
		String_t* L_29 = V_2;
		NullCheck(L_27);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_27, 6, L_28, L_29, 2, NULL);
		return;
	}

IL_007c:
	{
		// OnAdEvent(RiseSdk.AdEventType.RewardAdShowFailed, id, tag, RiseSdk.ADTYPE_VIDEO);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_30 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		int32_t L_31 = V_1;
		String_t* L_32 = V_2;
		NullCheck(L_30);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_30, 7, L_31, L_32, 2, NULL);
	}

IL_008a:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onFullAdClosed(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onFullAdClosed_m31E4867736B38DCD41BADBB53DAA59E65CFB2EEE (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_1 = NULL;
	{
		// if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		if (!L_0)
		{
			goto IL_0046;
		}
	}
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0046;
		}
	}
	{
		// string tag = "Default";
		V_0 = _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
		// if (!string.IsNullOrEmpty(data))
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478(L_3, NULL);
		if (L_4)
		{
			goto IL_0037;
		}
	}
	{
		// string[] msg = data.Split('|');
		String_t* L_5 = ___0_data;
		NullCheck(L_5);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6;
		L_6 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_5, ((int32_t)124), 0, NULL);
		V_1 = L_6;
		// if (msg != null && msg.Length > 0)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_7 = V_1;
		if (!L_7)
		{
			goto IL_0037;
		}
	}
	{
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_8 = V_1;
		NullCheck(L_8);
		if (!(((RuntimeArray*)L_8)->max_length))
		{
			goto IL_0037;
		}
	}
	{
		// tag = msg[0];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_1;
		NullCheck(L_9);
		int32_t L_10 = 0;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		V_0 = L_11;
	}

IL_0037:
	{
		// OnAdEvent(RiseSdk.AdEventType.FullAdClosed, -1, tag, RiseSdk.ADTYPE_INTERTITIAL);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_12 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		String_t* L_13 = V_0;
		NullCheck(L_12);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_12, ((int32_t)10), (-1), L_13, 1, NULL);
	}

IL_0046:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onFullAdClicked(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onFullAdClicked_mC82BF8F07E8FFE5208D3B1205BC72393FA208197 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_1 = NULL;
	{
		// if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		if (!L_0)
		{
			goto IL_0046;
		}
	}
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0046;
		}
	}
	{
		// string tag = "Default";
		V_0 = _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
		// if (!string.IsNullOrEmpty(data))
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478(L_3, NULL);
		if (L_4)
		{
			goto IL_0037;
		}
	}
	{
		// string[] msg = data.Split('|');
		String_t* L_5 = ___0_data;
		NullCheck(L_5);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6;
		L_6 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_5, ((int32_t)124), 0, NULL);
		V_1 = L_6;
		// if (msg != null && msg.Length > 0)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_7 = V_1;
		if (!L_7)
		{
			goto IL_0037;
		}
	}
	{
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_8 = V_1;
		NullCheck(L_8);
		if (!(((RuntimeArray*)L_8)->max_length))
		{
			goto IL_0037;
		}
	}
	{
		// tag = msg[0];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_1;
		NullCheck(L_9);
		int32_t L_10 = 0;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		V_0 = L_11;
	}

IL_0037:
	{
		// OnAdEvent(RiseSdk.AdEventType.FullAdClicked, -1, tag, RiseSdk.ADTYPE_INTERTITIAL);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_12 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		String_t* L_13 = V_0;
		NullCheck(L_12);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_12, ((int32_t)12), (-1), L_13, 1, NULL);
	}

IL_0046:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onAdShow(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onAdShow_mE759F1009B198548238F930C9C57AEC08F270C5D (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	int32_t V_1 = 0;
	int32_t V_2 = 0;
	int32_t V_3 = 0;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_4 = NULL;
	{
		// if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		if (!L_0)
		{
			goto IL_00a1;
		}
	}
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_00a1;
		}
	}
	{
		// string tag = "Default";
		V_0 = _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
		// int type = RiseSdk.ADTYPE_INTERTITIAL;
		V_1 = 1;
		// int id = -1;
		V_2 = (-1);
		// if (!string.IsNullOrEmpty(data))
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478(L_3, NULL);
		if (L_4)
		{
			goto IL_0066;
		}
	}
	{
		// string[] msg = data.Split('|');
		String_t* L_5 = ___0_data;
		NullCheck(L_5);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6;
		L_6 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_5, ((int32_t)124), 0, NULL);
		V_4 = L_6;
		// if (msg != null && msg.Length > 1)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_7 = V_4;
		if (!L_7)
		{
			goto IL_0066;
		}
	}
	{
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_8 = V_4;
		NullCheck(L_8);
		if ((((int32_t)((int32_t)(((RuntimeArray*)L_8)->max_length))) <= ((int32_t)1)))
		{
			goto IL_0066;
		}
	}
	{
		// int.TryParse(msg[0], out type);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_4;
		NullCheck(L_9);
		int32_t L_10 = 0;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		bool L_12;
		L_12 = Int32_TryParse_mC928DE2FEC1C35ED5298BDDCA9868076E94B8A21(L_11, (&V_1), NULL);
		// tag = msg[1];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_13 = V_4;
		NullCheck(L_13);
		int32_t L_14 = 1;
		String_t* L_15 = (L_13)->GetAt(static_cast<il2cpp_array_size_t>(L_14));
		V_0 = L_15;
		// if (msg.Length >= 3)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_16 = V_4;
		NullCheck(L_16);
		if ((((int32_t)((int32_t)(((RuntimeArray*)L_16)->max_length))) < ((int32_t)3)))
		{
			goto IL_0066;
		}
	}
	{
		// int.TryParse(msg[2], out id);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_17 = V_4;
		NullCheck(L_17);
		int32_t L_18 = 2;
		String_t* L_19 = (L_17)->GetAt(static_cast<il2cpp_array_size_t>(L_18));
		bool L_20;
		L_20 = Int32_TryParse_mC928DE2FEC1C35ED5298BDDCA9868076E94B8A21(L_19, (&V_2), NULL);
	}

IL_0066:
	{
		// RiseSdk.AdEventType eventType = RiseSdk.AdEventType.FullAdClicked;
		V_3 = ((int32_t)12);
		int32_t L_21 = V_1;
		switch (((int32_t)il2cpp_codegen_subtract(L_21, 1)))
		{
			case 0:
			{
				goto IL_0087;
			}
			case 1:
			{
				goto IL_008c;
			}
			case 2:
			{
				goto IL_0090;
			}
			case 3:
			{
				goto IL_0090;
			}
			case 4:
			{
				goto IL_0090;
			}
		}
	}
	{
		goto IL_0093;
	}

IL_0087:
	{
		// eventType = RiseSdk.AdEventType.FullAdShown;
		V_3 = ((int32_t)11);
		// break;
		goto IL_0093;
	}

IL_008c:
	{
		// eventType = RiseSdk.AdEventType.RewardAdShown;
		V_3 = 5;
		// break;
		goto IL_0093;
	}

IL_0090:
	{
		// eventType = RiseSdk.AdEventType.AdShown;
		V_3 = ((int32_t)17);
	}

IL_0093:
	{
		// OnAdEvent(eventType, id, tag, type);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_22 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		int32_t L_23 = V_3;
		int32_t L_24 = V_2;
		String_t* L_25 = V_0;
		int32_t L_26 = V_1;
		NullCheck(L_22);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_22, L_23, L_24, L_25, L_26, NULL);
	}

IL_00a1:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onAdClicked(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onAdClicked_m2415FF14B8E65304C2FC0F9FE56155C1FBEBB7CF (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	int32_t V_1 = 0;
	int32_t V_2 = 0;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_3 = NULL;
	{
		// if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		if (!L_0)
		{
			goto IL_008f;
		}
	}
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_008f;
		}
	}
	{
		// string tag = "Default";
		V_0 = _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
		// int adType = RiseSdk.ADTYPE_INTERTITIAL;
		V_1 = 1;
		// if (!string.IsNullOrEmpty(data))
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478(L_3, NULL);
		if (L_4)
		{
			goto IL_0049;
		}
	}
	{
		// string[] msg = data.Split('|');
		String_t* L_5 = ___0_data;
		NullCheck(L_5);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6;
		L_6 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_5, ((int32_t)124), 0, NULL);
		V_3 = L_6;
		// if (msg != null && msg.Length > 1)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_7 = V_3;
		if (!L_7)
		{
			goto IL_0049;
		}
	}
	{
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_8 = V_3;
		NullCheck(L_8);
		if ((((int32_t)((int32_t)(((RuntimeArray*)L_8)->max_length))) <= ((int32_t)1)))
		{
			goto IL_0049;
		}
	}
	{
		// int.TryParse(msg[0], out adType);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_3;
		NullCheck(L_9);
		int32_t L_10 = 0;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		bool L_12;
		L_12 = Int32_TryParse_mC928DE2FEC1C35ED5298BDDCA9868076E94B8A21(L_11, (&V_1), NULL);
		// tag = msg[1];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_13 = V_3;
		NullCheck(L_13);
		int32_t L_14 = 1;
		String_t* L_15 = (L_13)->GetAt(static_cast<il2cpp_array_size_t>(L_14));
		V_0 = L_15;
	}

IL_0049:
	{
		// RiseSdk.AdEventType eventType = RiseSdk.AdEventType.FullAdClicked;
		V_2 = ((int32_t)12);
		int32_t L_16 = V_1;
		switch (((int32_t)il2cpp_codegen_subtract(L_16, 1)))
		{
			case 0:
			{
				goto IL_006a;
			}
			case 1:
			{
				goto IL_006f;
			}
			case 2:
			{
				goto IL_0074;
			}
			case 3:
			{
				goto IL_0079;
			}
			case 4:
			{
				goto IL_007e;
			}
		}
	}
	{
		goto IL_0081;
	}

IL_006a:
	{
		// eventType = RiseSdk.AdEventType.FullAdClicked;
		V_2 = ((int32_t)12);
		// break;
		goto IL_0081;
	}

IL_006f:
	{
		// eventType = RiseSdk.AdEventType.RewardAdClicked;
		V_2 = ((int32_t)9);
		// break;
		goto IL_0081;
	}

IL_0074:
	{
		// eventType = RiseSdk.AdEventType.BannerAdClicked;
		V_2 = ((int32_t)13);
		// break;
		goto IL_0081;
	}

IL_0079:
	{
		// eventType = RiseSdk.AdEventType.IconAdClicked;
		V_2 = ((int32_t)20);
		// break;
		goto IL_0081;
	}

IL_007e:
	{
		// eventType = RiseSdk.AdEventType.NativeAdClicked;
		V_2 = ((int32_t)21);
	}

IL_0081:
	{
		// OnAdEvent(eventType, -1, tag, adType);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_17 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		int32_t L_18 = V_2;
		String_t* L_19 = V_0;
		int32_t L_20 = V_1;
		NullCheck(L_17);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_17, L_18, (-1), L_19, L_20, NULL);
	}

IL_008f:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onVideoAdClosed(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onVideoAdClosed_m76B07A143219C04003F48732F302511A4236B704 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_1 = NULL;
	{
		// if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		if (!L_0)
		{
			goto IL_0045;
		}
	}
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0045;
		}
	}
	{
		// string tag = "Default";
		V_0 = _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
		// if (!string.IsNullOrEmpty(data))
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478(L_3, NULL);
		if (L_4)
		{
			goto IL_0037;
		}
	}
	{
		// string[] msg = data.Split('|');
		String_t* L_5 = ___0_data;
		NullCheck(L_5);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6;
		L_6 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_5, ((int32_t)124), 0, NULL);
		V_1 = L_6;
		// if (msg != null && msg.Length > 0)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_7 = V_1;
		if (!L_7)
		{
			goto IL_0037;
		}
	}
	{
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_8 = V_1;
		NullCheck(L_8);
		if (!(((RuntimeArray*)L_8)->max_length))
		{
			goto IL_0037;
		}
	}
	{
		// tag = msg[0];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_1;
		NullCheck(L_9);
		int32_t L_10 = 0;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		V_0 = L_11;
	}

IL_0037:
	{
		// OnAdEvent(RiseSdk.AdEventType.RewardAdClosed, -1, tag, RiseSdk.ADTYPE_VIDEO);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_12 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		String_t* L_13 = V_0;
		NullCheck(L_12);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_12, 8, (-1), L_13, 2, NULL);
	}

IL_0045:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onBannerAdClicked(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onBannerAdClicked_m546706AEEEE6682BFD9AF1207A5D59689D627E02 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_1 = NULL;
	{
		// if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		if (!L_0)
		{
			goto IL_0046;
		}
	}
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0046;
		}
	}
	{
		// string tag = "Default";
		V_0 = _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
		// if (!string.IsNullOrEmpty(data))
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478(L_3, NULL);
		if (L_4)
		{
			goto IL_0037;
		}
	}
	{
		// string[] msg = data.Split('|');
		String_t* L_5 = ___0_data;
		NullCheck(L_5);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6;
		L_6 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_5, ((int32_t)124), 0, NULL);
		V_1 = L_6;
		// if (msg != null && msg.Length > 0)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_7 = V_1;
		if (!L_7)
		{
			goto IL_0037;
		}
	}
	{
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_8 = V_1;
		NullCheck(L_8);
		if (!(((RuntimeArray*)L_8)->max_length))
		{
			goto IL_0037;
		}
	}
	{
		// tag = msg[0];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_1;
		NullCheck(L_9);
		int32_t L_10 = 0;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		V_0 = L_11;
	}

IL_0037:
	{
		// OnAdEvent(RiseSdk.AdEventType.BannerAdClicked, -1, tag, RiseSdk.ADTYPE_BANNER);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_12 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		String_t* L_13 = V_0;
		NullCheck(L_12);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_12, ((int32_t)13), (-1), L_13, 3, NULL);
	}

IL_0046:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::onCrossAdClicked(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_onCrossAdClicked_mD9690B55932522D33D1C3058453369397C589B81 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_1 = NULL;
	{
		// if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		if (!L_0)
		{
			goto IL_0046;
		}
	}
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_0046;
		}
	}
	{
		// string tag = "Default";
		V_0 = _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
		// if (!string.IsNullOrEmpty(data))
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478(L_3, NULL);
		if (L_4)
		{
			goto IL_0037;
		}
	}
	{
		// string[] msg = data.Split('|');
		String_t* L_5 = ___0_data;
		NullCheck(L_5);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6;
		L_6 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_5, ((int32_t)124), 0, NULL);
		V_1 = L_6;
		// if (msg != null && msg.Length > 0)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_7 = V_1;
		if (!L_7)
		{
			goto IL_0037;
		}
	}
	{
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_8 = V_1;
		NullCheck(L_8);
		if (!(((RuntimeArray*)L_8)->max_length))
		{
			goto IL_0037;
		}
	}
	{
		// tag = msg[0];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_9 = V_1;
		NullCheck(L_9);
		int32_t L_10 = 0;
		String_t* L_11 = (L_9)->GetAt(static_cast<il2cpp_array_size_t>(L_10));
		V_0 = L_11;
	}

IL_0037:
	{
		// OnAdEvent(RiseSdk.AdEventType.CrossAdClicked, -1, tag, RiseSdk.ADTYPE_OTHER);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_12 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		String_t* L_13 = V_0;
		NullCheck(L_12);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_12, ((int32_t)14), (-1), L_13, (-1), NULL);
	}

IL_0046:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::adLoaded(System.String)
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener_adLoaded_m33B583535259AA5970B5E6D9DA93ABCB93203209 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, String_t* ___0_data, const RuntimeMethod* method) 
{
	static bool s_Il2CppMethodInitialized;
	if (!s_Il2CppMethodInitialized)
	{
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var);
		il2cpp_codegen_initialize_runtime_metadata((uintptr_t*)&_stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E);
		s_Il2CppMethodInitialized = true;
	}
	String_t* V_0 = NULL;
	int32_t V_1 = 0;
	StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* V_2 = NULL;
	{
		// if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_0 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		if (!L_0)
		{
			goto IL_005e;
		}
	}
	{
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_1 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		NullCheck(L_1);
		DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* L_2;
		L_2 = VirtualFuncInvoker0< DelegateU5BU5D_tC5AB7E8F745616680F337909D3A8E6C722CDF771* >::Invoke(9 /* System.Delegate[] System.Delegate::GetInvocationList() */, L_1);
		NullCheck(L_2);
		if (!(((RuntimeArray*)L_2)->max_length))
		{
			goto IL_005e;
		}
	}
	{
		// string tag = "Default";
		V_0 = _stringLiteral9D5313975DA8153FF9A5262016CB413A4E84846E;
		// int adType = -1;
		V_1 = (-1);
		// if (!string.IsNullOrEmpty(data))
		String_t* L_3 = ___0_data;
		bool L_4;
		L_4 = String_IsNullOrEmpty_mEA9E3FB005AC28FE02E69FCF95A7B8456192B478(L_3, NULL);
		if (L_4)
		{
			goto IL_004f;
		}
	}
	{
		// string[] str = data.Split('|');
		String_t* L_5 = ___0_data;
		NullCheck(L_5);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_6;
		L_6 = String_Split_m9530B73D02054692283BF35C3A27C8F2230946F4(L_5, ((int32_t)124), 0, NULL);
		V_2 = L_6;
		// if (str.Length == 1)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_7 = V_2;
		NullCheck(L_7);
		if ((!(((uint32_t)((int32_t)(((RuntimeArray*)L_7)->max_length))) == ((uint32_t)1))))
		{
			goto IL_003a;
		}
	}
	{
		// tag = str[0];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_8 = V_2;
		NullCheck(L_8);
		int32_t L_9 = 0;
		String_t* L_10 = (L_8)->GetAt(static_cast<il2cpp_array_size_t>(L_9));
		V_0 = L_10;
		goto IL_004f;
	}

IL_003a:
	{
		// else if (str.Length >= 2)
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_11 = V_2;
		NullCheck(L_11);
		if ((((int32_t)((int32_t)(((RuntimeArray*)L_11)->max_length))) < ((int32_t)2)))
		{
			goto IL_004f;
		}
	}
	{
		// tag = str[0];
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_12 = V_2;
		NullCheck(L_12);
		int32_t L_13 = 0;
		String_t* L_14 = (L_12)->GetAt(static_cast<il2cpp_array_size_t>(L_13));
		V_0 = L_14;
		// int.TryParse(str[1], out adType);
		StringU5BU5D_t7674CD946EC0CE7B3AE0BE70E6EE85F2ECD9F248* L_15 = V_2;
		NullCheck(L_15);
		int32_t L_16 = 1;
		String_t* L_17 = (L_15)->GetAt(static_cast<il2cpp_array_size_t>(L_16));
		bool L_18;
		L_18 = Int32_TryParse_mC928DE2FEC1C35ED5298BDDCA9868076E94B8A21(L_17, (&V_1), NULL);
	}

IL_004f:
	{
		// OnAdEvent(RiseSdk.AdEventType.AdLoadCompleted, -1, tag, adType);
		Action_4_tD3A9D10E29A0661B283E829E2F2B4C4BCCC3C7A5* L_19 = ((RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_StaticFields*)il2cpp_codegen_static_fields_for(RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1_il2cpp_TypeInfo_var))->___OnAdEvent_14;
		String_t* L_20 = V_0;
		int32_t L_21 = V_1;
		NullCheck(L_19);
		Action_4_Invoke_mA28A4622C77F87C4459F901FC9FB43C01974F3D1_inline(L_19, ((int32_t)15), (-1), L_20, L_21, NULL);
	}

IL_005e:
	{
		// }
		return;
	}
}
// System.Void RiseSdk.RiseSdkListener::.ctor()
IL2CPP_EXTERN_C IL2CPP_METHOD_ATTR void RiseSdkListener__ctor_m8FFFA05A03EFE32044837CAE01E1A20F6CF2DFC9 (RiseSdkListener_tFDE052EF130CDBAFEDB5353CCB8CA9485FD82DD1* __this, const RuntimeMethod* method) 
{
	{
		MonoBehaviour__ctor_m592DB0105CA0BC97AA1C5F4AD27B12D68A3B7C1E(__this, NULL);
		return;
	}
}
#ifdef __clang__
#pragma clang diagnostic pop
#endif
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR int32_t String_get_Length_m42625D67623FA5CC7A44D47425CE86FB946542D2_inline (String_t* __this, const RuntimeMethod* method) 
{
	{
		int32_t L_0 = __this->____stringLength_4;
		return L_0;
	}
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_Invoke_m7126A54DACA72B845424072887B5F3A51FC3808E_inline (Action_tD00B0A84D7945E50C2DFFC28EFEE6ED44ED2AD07* __this, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl_1)((Il2CppObject*)__this->___method_code_6, reinterpret_cast<RuntimeMethod*>(__this->___method_3));
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR KeyValuePair_2_tFC32D2507216293851350D29B64D79F950B55230 Enumerator_get_Current_mE3475384B761E1C7971D3639BD09117FE8363422_gshared_inline (Enumerator_tEA93FE2B778D098F590CA168BEFC4CD85D73A6B9* __this, const RuntimeMethod* method) 
{
	{
		KeyValuePair_2_tFC32D2507216293851350D29B64D79F950B55230 L_0 = __this->____current_3;
		return L_0;
	}
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR RuntimeObject* KeyValuePair_2_get_Key_mBD8EA7557C27E6956F2AF29DA3F7499B2F51A282_gshared_inline (KeyValuePair_2_tFC32D2507216293851350D29B64D79F950B55230* __this, const RuntimeMethod* method) 
{
	{
		RuntimeObject* L_0 = __this->___key_0;
		return L_0;
	}
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR RuntimeObject* KeyValuePair_2_get_Value_mC6BD8075F9C9DDEF7B4D731E5C38EC19103988E7_gshared_inline (KeyValuePair_2_tFC32D2507216293851350D29B64D79F950B55230* __this, const RuntimeMethod* method) 
{
	{
		RuntimeObject* L_0 = __this->___value_1;
		return L_0;
	}
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void List_1_Add_mEBCF994CC3814631017F46A387B1A192ED6C85C7_gshared_inline (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D* __this, RuntimeObject* ___0_item, const RuntimeMethod* method) 
{
	ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* V_0 = NULL;
	int32_t V_1 = 0;
	{
		int32_t L_0 = __this->____version_3;
		__this->____version_3 = ((int32_t)il2cpp_codegen_add(L_0, 1));
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_1 = __this->____items_1;
		V_0 = L_1;
		int32_t L_2 = __this->____size_2;
		V_1 = L_2;
		int32_t L_3 = V_1;
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_4 = V_0;
		NullCheck(L_4);
		if ((!(((uint32_t)L_3) < ((uint32_t)((int32_t)(((RuntimeArray*)L_4)->max_length))))))
		{
			goto IL_0034;
		}
	}
	{
		int32_t L_5 = V_1;
		__this->____size_2 = ((int32_t)il2cpp_codegen_add(L_5, 1));
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_6 = V_0;
		int32_t L_7 = V_1;
		RuntimeObject* L_8 = ___0_item;
		NullCheck(L_6);
		(L_6)->SetAt(static_cast<il2cpp_array_size_t>(L_7), (RuntimeObject*)L_8);
		return;
	}

IL_0034:
	{
		RuntimeObject* L_9 = ___0_item;
		((  void (*) (List_1_tA239CB83DE5615F348BB0507E45F490F4F7C9A8D*, RuntimeObject*, const RuntimeMethod*))il2cpp_codegen_get_method_pointer(il2cpp_rgctx_method(method->klass->rgctx_data, 11)))(__this, L_9, il2cpp_rgctx_method(method->klass->rgctx_data, 11));
		return;
	}
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_1_Invoke_mF2422B2DD29F74CE66F791C3F68E288EC7C3DB9E_gshared_inline (Action_1_t6F9EB113EB3F16226AEF811A2744F4111C116C87* __this, RuntimeObject* ___0_obj, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, RuntimeObject*, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl_1)((Il2CppObject*)__this->___method_code_6, ___0_obj, reinterpret_cast<RuntimeMethod*>(__this->___method_3));
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* Array_Empty_TisRuntimeObject_mFB8A63D602BB6974D31E20300D9EB89C6FE7C278_gshared_inline (const RuntimeMethod* method) 
{
	{
		il2cpp_codegen_runtime_class_init_inline(il2cpp_rgctx_data(method->rgctx_data, 0));
		ObjectU5BU5D_t8061030B0A12A55D5AD8652A20C922FE99450918* L_0 = ((EmptyArray_1_tDF0DD7256B115243AA6BD5558417387A734240EE_StaticFields*)il2cpp_codegen_static_fields_for(il2cpp_rgctx_data(method->rgctx_data, 0)))->___Value_0;
		return L_0;
	}
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_1_Invoke_m69C8773D6967F3B224777183E24EA621CE056F8F_gshared_inline (Action_1_t10DCB0C07D0D3C565CEACADC80D1152B35A45F6C* __this, bool ___0_obj, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, bool, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl_1)((Il2CppObject*)__this->___method_code_6, ___0_obj, reinterpret_cast<RuntimeMethod*>(__this->___method_3));
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_2_Invoke_m05605BFAA1396765DE23112419D8E6F797B65101_gshared_inline (Action_2_t829FF86EEA250A853DF3A95B2C4DAC034707E20F* __this, int32_t ___0_arg1, RuntimeObject* ___1_arg2, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, int32_t, RuntimeObject*, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl_1)((Il2CppObject*)__this->___method_code_6, ___0_arg1, ___1_arg2, reinterpret_cast<RuntimeMethod*>(__this->___method_3));
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_2_Invoke_m3BE7DD18C4D59E8D0ECBBA5ED30F084F842415DD_gshared_inline (Action_2_t7B1DA294236CEB03E01D057BD5D4E8DCFCBF1811* __this, int32_t ___0_arg1, int32_t ___1_arg2, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, int32_t, int32_t, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl_1)((Il2CppObject*)__this->___method_code_6, ___0_arg1, ___1_arg2, reinterpret_cast<RuntimeMethod*>(__this->___method_3));
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_4_Invoke_mDCB9B02521F18F1D835EB717E6893B5675F67FEB_gshared_inline (Action_4_t11E5CF24FE986CDC1E7FA7474E067F17AFB2B206* __this, bool ___0_arg1, bool ___1_arg2, RuntimeObject* ___2_arg3, RuntimeObject* ___3_arg4, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, bool, bool, RuntimeObject*, RuntimeObject*, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl_1)((Il2CppObject*)__this->___method_code_6, ___0_arg1, ___1_arg2, ___2_arg3, ___3_arg4, reinterpret_cast<RuntimeMethod*>(__this->___method_3));
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_3_Invoke_m702C8A42A864B27E16DC823C64CDA08C26EA0DB5_gshared_inline (Action_3_tF1ADD96839CDCE68A6D0F397099A1E67C05F263B* __this, int32_t ___0_arg1, bool ___1_arg2, RuntimeObject* ___2_arg3, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, int32_t, bool, RuntimeObject*, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl_1)((Il2CppObject*)__this->___method_code_6, ___0_arg1, ___1_arg2, ___2_arg3, reinterpret_cast<RuntimeMethod*>(__this->___method_3));
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_3_Invoke_mD83B53A0DF001DB829C663514DEB5AC437FEFAF6_gshared_inline (Action_3_t2740A37A77E4652A559511D11F6662C28174A796* __this, bool ___0_arg1, int32_t ___1_arg2, RuntimeObject* ___2_arg3, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, bool, int32_t, RuntimeObject*, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl_1)((Il2CppObject*)__this->___method_code_6, ___0_arg1, ___1_arg2, ___2_arg3, reinterpret_cast<RuntimeMethod*>(__this->___method_3));
}
IL2CPP_MANAGED_FORCE_INLINE IL2CPP_METHOD_ATTR void Action_4_Invoke_mF50DBFC8D3619C368487D86A807E3C2205D1433D_gshared_inline (Action_4_t50E2C24F41BECB3E65BEB8FD155874C71203093C* __this, int32_t ___0_arg1, int32_t ___1_arg2, RuntimeObject* ___2_arg3, int32_t ___3_arg4, const RuntimeMethod* method) 
{
	typedef void (*FunctionPointerType) (RuntimeObject*, int32_t, int32_t, RuntimeObject*, int32_t, const RuntimeMethod*);
	((FunctionPointerType)__this->___invoke_impl_1)((Il2CppObject*)__this->___method_code_6, ___0_arg1, ___1_arg2, ___2_arg3, ___3_arg4, reinterpret_cast<RuntimeMethod*>(__this->___method_3));
}
