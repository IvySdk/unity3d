// Generated code. Do not modify.

import CommonCorePublic
import Foundation
import Serialization

public final class UrlVariableTemplate: TemplateValue {
  public static let type: String = "url"
  public let parent: String? // at least 1 char
  public let name: Field<String>? // at least 1 char
  public let value: Field<URL>?

  static let parentValidator: AnyValueValidator<String> =
    makeStringValidator(minLength: 1)

  public convenience init(dictionary: [String: Any], templateToType: [TemplateName: String]) throws {
    do {
      self.init(
        parent: try dictionary.getOptionalField("type", validator: Self.parentValidator),
        name: try dictionary.getOptionalField("name"),
        value: try dictionary.getOptionalField("value", transform: URL.init(string:))
      )
    } catch let DeserializationError.invalidFieldRepresentation(field: field, representation: representation) {
      throw DeserializationError.invalidFieldRepresentation(field: "url_variable_template." + field, representation: representation)
    }
  }

  init(
    parent: String?,
    name: Field<String>? = nil,
    value: Field<URL>? = nil
  ) {
    self.parent = parent
    self.name = name
    self.value = value
  }

  private static func resolveOnlyLinks(context: TemplatesContext, parent: UrlVariableTemplate?) -> DeserializationResult<UrlVariable> {
    let nameValue = parent?.name?.resolveValue(context: context, validator: ResolvedValue.nameValidator) ?? .noValue
    let valueValue = parent?.value?.resolveValue(context: context, transform: URL.init(string:)) ?? .noValue
    var errors = mergeErrors(
      nameValue.errorsOrWarnings?.map { .nestedObjectError(field: "name", error: $0) },
      valueValue.errorsOrWarnings?.map { .nestedObjectError(field: "value", error: $0) }
    )
    if case .noValue = nameValue {
      errors.append(.requiredFieldIsMissing(field: "name"))
    }
    if case .noValue = valueValue {
      errors.append(.requiredFieldIsMissing(field: "value"))
    }
    guard
      let nameNonNil = nameValue.value,
      let valueNonNil = valueValue.value
    else {
      return .failure(NonEmptyArray(errors)!)
    }
    let result = UrlVariable(
      name: nameNonNil,
      value: valueNonNil
    )
    return errors.isEmpty ? .success(result) : .partialSuccess(result, warnings: NonEmptyArray(errors)!)
  }

  public static func resolveValue(context: TemplatesContext, parent: UrlVariableTemplate?, useOnlyLinks: Bool) -> DeserializationResult<UrlVariable> {
    if useOnlyLinks {
      return resolveOnlyLinks(context: context, parent: parent)
    }
    var nameValue: DeserializationResult<String> = parent?.name?.value(validatedBy: ResolvedValue.nameValidator) ?? .noValue
    var valueValue: DeserializationResult<URL> = parent?.value?.value() ?? .noValue
    context.templateData.forEach { key, __dictValue in
      switch key {
      case "name":
        nameValue = deserialize(__dictValue, validator: ResolvedValue.nameValidator).merged(with: nameValue)
      case "value":
        valueValue = deserialize(__dictValue, transform: URL.init(string:)).merged(with: valueValue)
      case parent?.name?.link:
        nameValue = nameValue.merged(with: deserialize(__dictValue, validator: ResolvedValue.nameValidator))
      case parent?.value?.link:
        valueValue = valueValue.merged(with: deserialize(__dictValue, transform: URL.init(string:)))
      default: break
      }
    }
    var errors = mergeErrors(
      nameValue.errorsOrWarnings?.map { .nestedObjectError(field: "name", error: $0) },
      valueValue.errorsOrWarnings?.map { .nestedObjectError(field: "value", error: $0) }
    )
    if case .noValue = nameValue {
      errors.append(.requiredFieldIsMissing(field: "name"))
    }
    if case .noValue = valueValue {
      errors.append(.requiredFieldIsMissing(field: "value"))
    }
    guard
      let nameNonNil = nameValue.value,
      let valueNonNil = valueValue.value
    else {
      return .failure(NonEmptyArray(errors)!)
    }
    let result = UrlVariable(
      name: nameNonNil,
      value: valueNonNil
    )
    return errors.isEmpty ? .success(result) : .partialSuccess(result, warnings: NonEmptyArray(errors)!)
  }

  private func mergedWithParent(templates: [TemplateName: Any]) throws -> UrlVariableTemplate {
    guard let parent = parent, parent != Self.type else { return self }
    guard let parentTemplate = templates[parent] as? UrlVariableTemplate else {
      throw DeserializationError.unknownType(type: parent)
    }
    let mergedParent = try parentTemplate.mergedWithParent(templates: templates)

    return UrlVariableTemplate(
      parent: nil,
      name: name ?? mergedParent.name,
      value: value ?? mergedParent.value
    )
  }

  public func resolveParent(templates: [TemplateName: Any]) throws -> UrlVariableTemplate {
    return try mergedWithParent(templates: templates)
  }
}
